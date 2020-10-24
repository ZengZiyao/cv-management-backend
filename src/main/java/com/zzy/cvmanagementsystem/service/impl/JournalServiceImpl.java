package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import com.zzy.cvmanagementsystem.repository.JournalRepository;
import com.zzy.cvmanagementsystem.service.JournalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    private JournalRepository journalRepository;

    JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public void addJournal(String name) {
        JournalDao journalDao = new JournalDao();
        journalDao.setName(name);

        journalRepository.save(journalDao);
    }

    @Override
    public List<JournalDao> getAllJournals() {
        return journalRepository.findAll();
    }
}