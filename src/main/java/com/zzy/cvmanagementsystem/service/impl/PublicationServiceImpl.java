package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.ConnectionDao;
import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dao.StatusDao;
import com.zzy.cvmanagementsystem.dao.UserDao;
import com.zzy.cvmanagementsystem.dto.PublicationDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.model.PubSource;
import com.zzy.cvmanagementsystem.model.PubType;
import com.zzy.cvmanagementsystem.repository.*;
import com.zzy.cvmanagementsystem.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;
    private JournalRepository journalRepository;
    private ConferenceRepository conferenceRepository;
    private ConnectionRepository connectionRepository;
    private StatusRepository statusRepository;
    private UserRepository userRepository;

    PublicationServiceImpl(PublicationRepository publicationRepository, JournalRepository journalRepository, ConferenceRepository conferenceRepository,
                           ConnectionRepository connectionRepository,
                           StatusRepository statusRepository,
                           UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.journalRepository = journalRepository;
        this.conferenceRepository = conferenceRepository;
        this.connectionRepository = connectionRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addPublication(PublicationDto publicationDto, String userid) {
        PublicationDao publicationDao = new PublicationDao();
        publicationDao.setAuthors(publicationDto.getAuthors());
        publicationDao.setDate(publicationDto.getDate());
        publicationDao.setType(publicationDto.getType());
        publicationDao.setCountry(publicationDto.getCountry());
        publicationDao.setSourceId(publicationDto.getPubSource().getId());
        publicationDao.setPage(publicationDto.getPage());
        publicationDao.setTier(publicationDto.getTier());
        publicationDao.setTitle(publicationDto.getTitle());
        publicationDao.setUserId(userid);
        savePublication(publicationDao);
    }

    private void savePublication(PublicationDao publicationDao) {
        publicationRepository.save(publicationDao);

        ConnectionDao connectionDao = new ConnectionDao();
        connectionDao.setUserId(publicationDao.getUserId());
        List<ConnectionDao> connectionDaoList = connectionRepository.findAll(Example.of(connectionDao));
        for (ConnectionDao c :
                connectionDaoList) {
            StatusDao statusDao = statusRepository.findByUserId(c.getFollowerId());
            UserDao userDao = userRepository.findById(c.getFollowerId()).orElse(null);
            if (statusDao != null
                    && statusDao.isPublication()
                    && userDao != null
                    && publicationDao.getAuthors().stream().anyMatch((a) -> a.getName().equals(userDao.getShortname()))
                    && getAllPublication(c.getFollowerId()).stream().noneMatch(p -> p.getTitle().equals(publicationDao.getTitle()))) {
                publicationDao.setUserId(c.getFollowerId());
                publicationDao.setId(null);
                savePublication(publicationDao);
            }
        }
    }

    @Override
    public void updatePublication(String id, PublicationDto publicationDto) {
        PublicationDao publicationDao = publicationRepository.findById(id).orElseThrow(() -> new NotFoundException("Publication not found"));
        updatePublicationDao(publicationDao, publicationDto);
        publicationRepository.save(publicationDao);

    }

    private void updatePublicationDao(PublicationDao publicationDao, PublicationDto publicationDto) {
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
    }

    @Override
    public void deletePublication(String id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public List<PublicationDto> getAllPublication(String userid) {
        List<PublicationDao> publicationDaoList = publicationRepository.findAllByUserId(userid);
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

    @Override
    public void deleteByUserId(String userid) {
        publicationRepository.deleteAll(publicationRepository.findAllByUserId(userid));
    }
}
