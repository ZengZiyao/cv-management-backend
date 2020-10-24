package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dto.PublicationDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.PublicationRepository;
import com.zzy.cvmanagementsystem.service.PublicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;

    PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public void addPublication(PublicationDto publicationDto) {
        PublicationDao publicationDao = new PublicationDao();
        publicationDao.setAuthors(publicationDto.getAuthors());
        publicationDao.setDate(publicationDto.getDate());
        publicationDao.setJournal(publicationDto.getJournal());
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
        publicationDao.setJournal(publicationDto.getJournal());
        publicationDao.setPage(publicationDto.getPage());
        publicationDao.setTier(publicationDto.getTier());
        publicationDao.setTitle(publicationDto.getTitle());

        publicationRepository.save(publicationDao);

    }

    @Override
    public void deletePublication(String id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public List<PublicationDao> getAllPublication() {
        return publicationRepository.findAll();
    }
}
