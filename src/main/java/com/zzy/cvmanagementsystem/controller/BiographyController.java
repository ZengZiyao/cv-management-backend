package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.BiographyDto;
import com.zzy.cvmanagementsystem.service.BiographyService;
import com.zzy.cvmanagementsystem.service.impl.BiographyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cv/biography")
@CrossOrigin
public class BiographyController {

    private BiographyService biographyService;

    BiographyController(BiographyServiceImpl biographyService) {
        this.biographyService = biographyService;
    }

    @GetMapping("")
    public ResponseEntity getBiography() {
        return ResponseEntity.ok(biographyService.getBiography());
    }

    @PostMapping("")
    public ResponseEntity addBiography(@RequestBody BiographyDto biographyDto) {
        biographyService.addBiography(biographyDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity updateBiography(@RequestBody BiographyDto biographyDto) {
        biographyService.updateBiography(biographyDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity deleteBiography() {
        biographyService.deleteBiography();

        return ResponseEntity.noContent().build();
    }
}
