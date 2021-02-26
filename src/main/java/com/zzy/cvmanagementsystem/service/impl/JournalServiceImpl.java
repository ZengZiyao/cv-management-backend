package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dto.JournalDto;
import com.zzy.cvmanagementsystem.model.PubType;
import com.zzy.cvmanagementsystem.repository.JournalRepository;
import com.zzy.cvmanagementsystem.repository.PublicationRepository;
import com.zzy.cvmanagementsystem.service.JournalService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalServiceImpl implements JournalService {

    private JournalRepository journalRepository;
    private PublicationRepository publicationRepository;

    JournalServiceImpl(JournalRepository journalRepository, PublicationRepository publicationRepository) {
        this.journalRepository = journalRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public void addJournal(String name, String userid) {
        JournalDao journalDao = new JournalDao();
        journalDao.setName(name);
        journalDao.setUserId(userid);
        journalRepository.save(journalDao);
    }

    @Override
    public List<JournalDto> getAllJournals(String userid) {
        List<JournalDto> journalDtoList = new ArrayList<>();
        List<JournalDao> journalDaoList = journalRepository.findByUserId(userid);
        for (JournalDao journalDao : journalDaoList) {
            JournalDto journalDto = new JournalDto();
            journalDto.setId(journalDao.getId());
            journalDto.setName(journalDao.getName());
            journalDtoList.add(journalDto);
        }
        return journalDtoList;
    }

    @Override
    public void updateJournals(String userid, List<JournalDto> journalDtos) {

        List<JournalDao> oldJournalDaoList = journalRepository.findByUserId(userid);
        Map<String, JournalDao> oldJournalMap = new HashMap<>();
        for (JournalDao journalDao : oldJournalDaoList) {
            oldJournalMap.put(journalDao.getId(), journalDao);
        }

        Map<String, Boolean> newJournalIdMap = new HashMap<>();
        List<JournalDao> newJournalDaoList = new ArrayList<>();
        for (JournalDto journalDto : journalDtos) {
            JournalDao j = new JournalDao();
            if (journalDto.getId() != null) {
                newJournalIdMap.put(journalDto.getId(), true);

                if (oldJournalMap.containsKey(journalDto.getId())) {
                    j.setId(journalDto.getId());
                } else {
                    continue;
                }

            }

            j.setUserId(userid);
            j.setName(journalDto.getName());
            newJournalDaoList.add(j);
        }

        journalRepository.deleteAll(oldJournalDaoList);
        journalRepository.saveAll(newJournalDaoList);

        PublicationDao example = new PublicationDao();
        example.setType(PubType.JOURNAL.ordinal());
        example.setUserId(userid);
        List<PublicationDao> publicationDaoList = publicationRepository.findAll(Example.of(example));
        publicationRepository.deleteAll(publicationDaoList);
        for (PublicationDao publicationDao : publicationDaoList) {
            if (!newJournalIdMap.containsKey(publicationDao.getSourceId())) {
                publicationDao.setSourceId(null);
            }
        }
        publicationRepository.saveAll(publicationDaoList);
    }
}
