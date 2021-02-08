package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dto.PublicationDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.model.PubSource;
import com.zzy.cvmanagementsystem.model.PubType;
import com.zzy.cvmanagementsystem.repository.ConferenceRepository;
import com.zzy.cvmanagementsystem.repository.JournalRepository;
import com.zzy.cvmanagementsystem.repository.PublicationRepository;
import com.zzy.cvmanagementsystem.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;
    private JournalRepository journalRepository;
    private ConferenceRepository conferenceRepository;

    PublicationServiceImpl(PublicationRepository publicationRepository, JournalRepository journalRepository, ConferenceRepository conferenceRepository) {
        this.publicationRepository = publicationRepository;
        this.journalRepository = journalRepository;
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public void addPublication(PublicationDto publicationDto) {
        PublicationDao publicationDao = new PublicationDao();
        publicationDao.setAuthors(publicationDto.getAuthors());
        publicationDao.setDate(publicationDto.getDate());
        publicationDao.setType(publicationDto.getType());
        publicationDao.setCountry(publicationDto.getCountry());
        publicationDao.setSourceId(publicationDto.getPubSource().getId());
        publicationDao.setPage(publicationDto.getPage());
        publicationDao.setTier(publicationDto.getTier());
        publicationDao.setTitle(publicationDto.getTitle());

        publicationRepository.save(publicationDao);
    }

    @Override
    public void updatePublication(String id, PublicationDto publicationDto) {
        PublicationDao publicationDao = publicationRepository.findById(id).orElseThrow(() -> new NotFoundException("Publication not found"));
        publicationDao.setAuthors(publicationDto.getAuthors());
        publicationDao.setDate(publicationDto.getDate());
        publicationDao.setSourceId(publicationDto.getPubSource().getId());
        publicationDao.setPage(publicationDto.getPage());
        publicationDao.setTitle(publicationDto.getTitle());
        if (publicationDao.getType() != publicationDto.getType()) {
            if (publicationDto.getType() == PubType.JOURNAL.ordinal()) {
                publicationDao.setCountry(null);
                publicationDao.setTier(publicationDto.getTier());
            } else if (publicationDto.getType() == PubType.CONFERENCE.ordinal()) {
                publicationDao.setTier(null);
                publicationDao.setCountry(publicationDto.getCountry());
            }
        } else {
            publicationDao.setCountry(publicationDto.getCountry());
            publicationDao.setTier(publicationDto.getTier());
        }
        publicationDao.setType(publicationDto.getType());


        publicationRepository.save(publicationDao);

    }

    @Override
    public void deletePublication(String id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public List<PublicationDto> getAllPublication() {
        List<PublicationDao> publicationDaoList = publicationRepository.findAll();
        List<PublicationDto> publicationDtoList = new ArrayList<>();
        publicationDaoList.forEach((p) -> {
            PublicationDto publicationDto = new PublicationDto();
            publicationDto.setId(p.getId());
            publicationDto.setAuthors(p.getAuthors());
            publicationDto.setCountry(p.getCountry());
            publicationDto.setDate(p.getDate());
            publicationDto.setPage(p.getPage());
            publicationDto.setTier(p.getTier());
            publicationDto.setTitle(p.getTitle());
            publicationDto.setType(p.getType());
            PubSource pubSource = null;
            if (p.getType() == PubType.JOURNAL.ordinal() && p.getSourceId() != null) {
                pubSource = journalRepository.findById(p.getSourceId()).orElse(null);
            } else if (p.getType() == PubType.CONFERENCE.ordinal() && p.getSourceId() != null) {
                pubSource = conferenceRepository.findById(p.getSourceId()).orElse(null);
            }
            publicationDto.setPubSource(pubSource);

            publicationDtoList.add(publicationDto);
        });
        return publicationDtoList;
    }
}
