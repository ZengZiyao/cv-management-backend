package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JournalService {

    void addJournal(String name);

    List<JournalDao> getAllJournals();
}
