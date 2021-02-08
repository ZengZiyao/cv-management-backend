package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.AcademicQualificationDao;
import com.zzy.cvmanagementsystem.dto.AcademicQualificationDto;
import com.zzy.cvmanagementsystem.service.AcademicQualificationService;
import com.zzy.cvmanagementsystem.service.impl.AcademicQualificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/academicQualifications")
@CrossOrigin
@Slf4j
public class AcademicQualificationController {
    private AcademicQualificationService academicQualificationService;

    AcademicQualificationController(AcademicQualificationServiceImpl academicQualificationService) {
        this.academicQualificationService = academicQualificationService;
    }

    @GetMapping("")
    public ResponseEntity getAllAcademicQualifications() {
        List<AcademicQualificationDao> academicQualificationDaos = academicQualificationService.getAllAcademicQualifications();

        return ResponseEntity.ok(academicQualificationDaos);
    }

    @PostMapping("")
    public ResponseEntity addAcademicQualification(@RequestBody AcademicQualificationDto academicQualificationDto) {
        academicQualificationService.addAcademicQualification(academicQualificationDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAcademicQualification(@PathVariable String id, @RequestBody AcademicQualificationDto academicQualificationDto) {
        academicQualificationService.updateAcademicQualificationById(id, academicQualificationDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{academicQualificationId}")
    public ResponseEntity deleteAcademicQualification(@PathVariable String academicQualificationId) {
        academicQualificationService.deleteAcademicQualification(academicQualificationId);

        return ResponseEntity.noContent().build();
    }

}
