package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.JournalDto;
import com.zzy.cvmanagementsystem.service.JournalService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.JournalServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/journals")
@CrossOrigin
public class JournalController {
    private JournalService journalService;
    private UserService userService;

    JournalController(JournalServiceImpl journalService, UserServiceImpl userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllJournals(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<JournalDto> journalDtos = journalService.getAllJournals(userid);

        return ResponseEntity.ok(journalDtos);
    }

    @PostMapping("")
    public ResponseEntity addJournal(@RequestBody String name, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        journalService.addJournal(name, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity updateJournals(@RequestBody List<JournalDto> journalDtos, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        journalService.updateJournals(userid, journalDtos);

        return ResponseEntity.noContent().build();
    }
}
