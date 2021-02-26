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

import java.util.ArrayList;
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
    public void addConference(String name, String userid) {
        ConferenceDao conferenceDao = new ConferenceDao();
        conferenceDao.setName(name);
        conferenceDao.setUserId(userid);
        conferenceRepository.save(conferenceDao);
    }

    @Override
    public List<ConferenceDto> getAllConferences(String userid) {
        List<ConferenceDto> conferenceDtos = new ArrayList<>();
        List<ConferenceDao> conferenceDaos = conferenceRepository.findByUserId(userid);
        for (ConferenceDao conferenceDao : conferenceDaos) {
            ConferenceDto conferenceDto = new ConferenceDto();
            conferenceDto.setId(conferenceDao.getId());
            conferenceDto.setName(conferenceDao.getName());
            conferenceDto.setId(conferenceDao.getId());
            conferenceDtos.add(conferenceDto);
        }
        return conferenceDtos;
    }

    @Override
    public void updateConferences(String userid, List<ConferenceDto> conferenceDtos) {
        List<ConferenceDao> oldConferenceDaoList = conferenceRepository.findByUserId(userid);
        Map<String, ConferenceDao> oldConferenceMap = new HashMap<>();
        for (ConferenceDao conferenceDao : oldConferenceDaoList) {
            oldConferenceMap.put(conferenceDao.getId(), conferenceDao);
        }

        Map<String, Boolean> newConferenceIdMap = new HashMap<>();
        List<ConferenceDao> newConferenceDaoList = new ArrayList<>();
        for (ConferenceDto conferenceDto : conferenceDtos) {
            ConferenceDao c = new ConferenceDao();
            if (conferenceDto.getId() != null) {
                newConferenceIdMap.put(conferenceDto.getId(), true);

                if (oldConferenceMap.containsKey(conferenceDto.getId())) {
                    c.setId(conferenceDto.getId());
                } else {
                    continue;
                }

            }

            c.setUserId(userid);
            c.setName(conferenceDto.getName());
            newConferenceDaoList.add(c);
        }

        conferenceRepository.deleteAll(oldConferenceDaoList);
        conferenceRepository.saveAll(newConferenceDaoList);

        PublicationDao example = new PublicationDao();
        example.setType(PubType.CONFERENCE.ordinal());
        example.setUserId(userid);
        List<PublicationDao> publicationDaoList = publicationRepository.findAll(Example.of(example));
        publicationRepository.deleteAll(publicationDaoList);
        for (PublicationDao publicationDao : publicationDaoList) {
            if (!newConferenceIdMap.containsKey(publicationDao.getSourceId())) {
                publicationDao.setSourceId(null);
            }
        }
        publicationRepository.saveAll(publicationDaoList);

    }

}
