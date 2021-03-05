package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.ConferenceDto;
import com.zzy.cvmanagementsystem.service.ConferenceService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.ConferenceServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/conferences")
@CrossOrigin
public class ConferenceController {
    private ConferenceService conferenceService;
    private UserService userService;

    ConferenceController(ConferenceServiceImpl conferenceService, UserServiceImpl userService) {
        this.conferenceService = conferenceService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllConferences(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<ConferenceDto> conferenceDtos = conferenceService.getAllConferences(userid);

        return ResponseEntity.ok(conferenceDtos);
    }

    @PostMapping("")
    public ResponseEntity addConference(@RequestBody String name, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        conferenceService.addConference(name, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity updateConferences(@RequestBody List<ConferenceDto> conferenceDtos, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        conferenceService.updateConferences(userid, conferenceDtos);

        return ResponseEntity.noContent().build();
    }
}
