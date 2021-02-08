package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.ConferenceDao;
import com.zzy.cvmanagementsystem.dto.ConferenceDto;
import com.zzy.cvmanagementsystem.service.ConferenceService;
import com.zzy.cvmanagementsystem.service.impl.ConferenceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/conferences")
@CrossOrigin
public class ConferenceController {
    private ConferenceService conferenceService;

    ConferenceController(ConferenceServiceImpl conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("")
    public ResponseEntity getAllConferences() {
        List<ConferenceDao> conferenceDaos = conferenceService.getAllConferences();

        return ResponseEntity.ok(conferenceDaos);
    }

    @PostMapping("")
    public ResponseEntity addConference(@RequestBody String name) {
        conferenceService.addConference(name);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity updateConferences(@RequestBody List<ConferenceDto> conferenceDtos) {
        conferenceService.updateConferences(conferenceDtos);

        return ResponseEntity.noContent().build();
    }
}
