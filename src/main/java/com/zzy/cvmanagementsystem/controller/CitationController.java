package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.CitationDto;
import com.zzy.cvmanagementsystem.service.CitationService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.CitationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/citations")
@CrossOrigin
@Slf4j
public class CitationController {
    private CitationService citationService;
    private UserService userService;

    CitationController(CitationServiceImpl citationService, UserService userService) {
        this.citationService = citationService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getCitations(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();
        List<CitationDto> citationDtoList = citationService.getAllCitations(userid);
        return ResponseEntity.ok(citationDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCitation(@PathVariable String id, @RequestBody CitationDto citationDto) {
        citationService.updateCitation(id, citationDto);
        return ResponseEntity.noContent().build();
    }
}
