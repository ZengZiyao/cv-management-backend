package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.CitationDao;
import com.zzy.cvmanagementsystem.dto.CitationDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.model.Database;
import com.zzy.cvmanagementsystem.repository.CitationRepository;
import com.zzy.cvmanagementsystem.service.CitationService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitationServiceImpl implements CitationService {
    private CitationRepository citationRepository;

    CitationServiceImpl(CitationRepository citationRepository) {
        this.citationRepository = citationRepository;
    }

    @Override
    public List<CitationDto> getAllCitations(String userid) {
        List<CitationDao> citationDaoList = citationRepository.findAllByUserId(userid);
        List<CitationDto> citationDtoList = new ArrayList<>();

        for (CitationDao citationDao :
                citationDaoList) {
            CitationDto citationDto = new CitationDto(citationDao.getId(), citationDao.getDatabase().ordinal(), citationDao.getCountWithSelf(), citationDao.getCountWithoutSelf(), citationDao.getHIndex());
            citationDtoList.add(citationDto);
        }
        return citationDtoList;
    }

    @Override
    public void updateCitation(String id, CitationDto citationDto) {
        CitationDao citationDao = citationRepository.findById(id).orElseThrow(() -> new NotFoundException("citation not found"));
        citationDao.setCountWithoutSelf(citationDto.getCountWithoutSelf());
        citationDao.setCountWithSelf(citationDto.getCountWithSelf());
        citationDao.setHIndex(citationDto.getHIndex());
        citationRepository.save(citationDao);
    }

    @Override
    public void createCitations(String userid) {
        for (Database database :
                Database.values()) {
            CitationDao citationDao = new CitationDao();
            citationDao.setDatabase(database);
            citationDao.setUserId(userid);
            citationRepository.save(citationDao);
        }
    }

    @Override
    public void deleteCitationsByUser(String userid) {
        List<CitationDao> citationDaoList = citationRepository.findAllByUserId(userid);
        citationRepository.deleteAll(citationDaoList);
    }

    @Override
    public void updateGSCitation(String userid, CitationDto citationDto) {
        CitationDao c = new CitationDao();
        c.setUserId(userid);
        c.setDatabase(Database.GOOGLE_SCHOLAR);
        CitationDao citationDao = citationRepository.findAll(Example.of(c)).get(0);
        citationDao.setCountWithSelf(citationDto.getCountWithSelf());
        citationDao.setHIndex(citationDto.getHIndex());
        citationRepository.save(citationDao);
    }
}
