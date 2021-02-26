package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.BiographyDto;
import com.zzy.cvmanagementsystem.service.BiographyService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.BiographyServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cv/biography")
@CrossOrigin
public class BiographyController {

    private BiographyService biographyService;
    private UserService userService;

    BiographyController(BiographyServiceImpl biographyService, UserServiceImpl userService) {
        this.biographyService = biographyService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getBiography(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        return ResponseEntity.ok(biographyService.getBiography(userid));
    }

    @PostMapping("")
    public ResponseEntity addBiography(@RequestBody BiographyDto biographyDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();
        biographyService.addBiography(biographyDto, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBiography(@PathVariable String id, @RequestBody BiographyDto biographyDto) {
        biographyService.updateBiography(id, biographyDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBiography(@PathVariable String id) {

        biographyService.deleteBiography(id);

        return ResponseEntity.noContent().build();
    }
}
