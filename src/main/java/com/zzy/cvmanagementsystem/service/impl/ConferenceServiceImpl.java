package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.ConferenceDao;
import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dto.ConferenceDto;
import com.zzy.cvmanagementsystem.model.PubType;
import com.zzy.cvmanagementsystem.repository.ConferenceRepository;
import com.zzy.cvmanagementsystem.repository.PublicationRepository;
import com.zzy.cvmanagementsystem.service.ConferenceService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    private ConferenceRepository conferenceRepository;
    private PublicationRepository publicationRepository;

    ConferenceServiceImpl(ConferenceRepository conferenceRepository, PublicationRepository publicationRepository) {
        this.conferenceRepository = conferenceRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public void addConference(String name) {
        ConferenceDao conferenceDao = new ConferenceDao();
        conferenceDao.setName(name);
        conferenceRepository.save(conferenceDao);
    }

    @Override
    public List<ConferenceDao> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @Override
    public void updateConferences(List<ConferenceDto> conferenceDtos) {

        List<ConferenceDao> conferenceDaoList = conferenceRepository.findAll();
        Map<String, Boolean> deleteMap = new HashMap<>();
        for (ConferenceDao conferenceDao : conferenceDaoList) {
            ConferenceDto c = new ConferenceDto(conferenceDao.getId(), conferenceDao.getName());
            if (!conferenceDtos.contains(c)) {
                deleteMap.put(conferenceDao.getId(), true);
            }
        }
        PublicationDao example = new PublicationDao();
        example.setType(PubType.CONFERENCE.ordinal());
        List<PublicationDao> publicationDaoList = publicationRepository.findAll(Example.of(example));
        publicationRepository.deleteAll(publicationDaoList);
        for (PublicationDao publicationDao : publicationDaoList) {
            if (deleteMap.get(publicationDao.getSourceId())) {
                publicationDao.setSourceId(null);
            }
        }

        publicationRepository.saveAll(publicationDaoList);
        conferenceRepository.deleteAll();
        for (ConferenceDto conferenceDto : conferenceDtos) {
            ConferenceDao c = new ConferenceDao(conferenceDto.getId(), conferenceDto.getName());
            conferenceRepository.save(c);
        }
    }

}
