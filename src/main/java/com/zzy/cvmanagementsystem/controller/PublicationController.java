package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.PublicationDao;
import com.zzy.cvmanagementsystem.dto.PublicationDto;
import com.zzy.cvmanagementsystem.service.PublicationService;
import com.zzy.cvmanagementsystem.service.impl.PublicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/publications")
@CrossOrigin
public class PublicationController {
    private PublicationService publicationService;

    PublicationController(PublicationServiceImpl publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping("")
    public ResponseEntity getAllPublications() {
        List<PublicationDao> publicationDaoList = publicationService.getAllPublication();

        return ResponseEntity.ok(publicationDaoList);
    }

    @PostMapping("")
    public ResponseEntity addPublication(@RequestBody PublicationDto publicationDto) {
        publicationService.addPublication(publicationDto);

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
