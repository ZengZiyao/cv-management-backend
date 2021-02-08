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
    public void addJournal(java.lang.String name) {
        JournalDao journalDao = new JournalDao();
        journalDao.setName(name);

        journalRepository.save(journalDao);
    }

    @Override
    public List<JournalDao> getAllJournals() {
        return journalRepository.findAll();
    }

    @Override
    public void updateJournals(List<JournalDto> journalDtos) {

        List<JournalDao> journalDaoList = journalRepository.findAll();
        Map<String, Boolean> deleteMap = new HashMap<>();
        for (JournalDao journalDao : journalDaoList) {
            JournalDto j = new JournalDto(journalDao.getId(), journalDao.getName());
            if (!journalDtos.contains(j)) {
                deleteMap.put(journalDao.getId(), true);
            }
        }
        PublicationDao example = new PublicationDao();
        example.setType(PubType.JOURNAL.ordinal());
        List<PublicationDao> publicationDaoList = publicationRepository.findAll(Example.of(example));
        publicationRepository.deleteAll(publicationDaoList);
        for (PublicationDao publicationDao : publicationDaoList) {
            if (deleteMap.get(publicationDao.getSourceId())) {
                publicationDao.setSourceId(null);
            }
        }

        publicationRepository.saveAll(publicationDaoList);
        journalRepository.deleteAll();
        for (JournalDto journalDto : journalDtos) {
            JournalDao j = new JournalDao(journalDto.getId(), journalDto.getName());
            journalRepository.save(j);
        }
    }
}
