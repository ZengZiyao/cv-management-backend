package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import com.zzy.cvmanagementsystem.service.JournalService;
import com.zzy.cvmanagementsystem.service.impl.JournalServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/journals")
@CrossOrigin
public class JournalController {
    private JournalService journalService;

    JournalController(JournalServiceImpl journalService) {
        this.journalService = journalService;
    }

    @GetMapping("")
    public ResponseEntity getAllJournals() {
        List<JournalDao> journalDaos = journalService.getAllJournals();

        return ResponseEntity.ok(journalDaos);
    }

    @PostMapping("")
    public ResponseEntity addJournal(@RequestBody String name) {
        journalService.addJournal(name);

        return ResponseEntity.noContent().build();
    }
}
