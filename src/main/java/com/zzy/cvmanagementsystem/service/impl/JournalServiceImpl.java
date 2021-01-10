package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dto.JournalDto;
import com.zzy.cvmanagementsystem.repository.JournalRepository;
import com.zzy.cvmanagementsystem.repository.PublicationRepository;
import com.zzy.cvmanagementsystem.service.JournalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<String> idList = new ArrayList<>();
        int i = 0;
        while (i < journalDaoList.size()) {
            int finalI = i;
            JournalDto j = journalDtos.stream().filter(e -> e.getId().equals(journalDaoList.get(finalI).getId())).findAny().orElse(null);
            if (j != null) {
                journalDaoList.get(i).setName(j.getName());
                i++;
            } else {
                idList.add(journalDaoList.get(i).getId());
                journalRepository.delete(journalDaoList.get(i));
                journalDaoList.remove(i);
            }
        }

        List<PublicationDao> publicationDaoList = publicationRepository.findAll();
        for (PublicationDao publicationDao: publicationDaoList) {
            if (idList.contains(publicationDao.getJournalId())) {
                publicationDao.setJournalId(null);
            }
        }


        for (JournalDto journalDto: journalDtos) {
            if (journalDto.getId() == null || journalDto.getId().equals("")) {
                JournalDao j = new JournalDao();
                j.setName(journalDto.getName());
                journalDaoList.add(j);
            }
        }

        journalRepository.saveAll(journalDaoList);
    }
}
