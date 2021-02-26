package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.JournalDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JournalService {

    void addJournal(String name, String userid);

    List<JournalDto> getAllJournals(String userid);

    void updateJournals(String userid, List<JournalDto> journalDtos);
}
