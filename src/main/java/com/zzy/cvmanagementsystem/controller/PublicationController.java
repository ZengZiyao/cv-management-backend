package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.PublicationDto;
import com.zzy.cvmanagementsystem.service.PublicationService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.PublicationServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/publications")
@CrossOrigin
public class PublicationController {
    private PublicationService publicationService;
    private UserService userService;

    PublicationController(PublicationServiceImpl publicationService, UserServiceImpl userService) {
        this.publicationService = publicationService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllPublications(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<PublicationDto> publicationDtoList = publicationService.getAllPublication(userid);

        return ResponseEntity.ok(publicationDtoList);
    }

    @PostMapping("")
    public ResponseEntity addPublication(@RequestBody PublicationDto publicationDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        publicationService.addPublication(publicationDto, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pId}")
    public ResponseEntity updatePublication(@PathVariable String pId, @RequestBody PublicationDto publicationDto) {
        publicationService.updatePublication(pId, publicationDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{pId}")
    public ResponseEntity deletePublicationById(@PathVariable String pId) {
        publicationService.deletePublication(pId);

        return ResponseEntity.noContent().build();
    }
}
