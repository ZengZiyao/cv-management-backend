package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.AcademicQualificationDto;
import com.zzy.cvmanagementsystem.service.AcademicQualificationService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.AcademicQualificationServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/academicQualifications")
@CrossOrigin
@Slf4j
public class AcademicQualificationController {
    private AcademicQualificationService academicQualificationService;
    private UserService userService;

    AcademicQualificationController(AcademicQualificationServiceImpl academicQualificationService, UserServiceImpl userService) {
        this.academicQualificationService = academicQualificationService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllAcademicQualifications(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<AcademicQualificationDto> academicQualificationDtos = academicQualificationService.getAllAcademicQualifications(userid);

        return ResponseEntity.ok(academicQualificationDtos);
    }

    @PostMapping("")
    public ResponseEntity addAcademicQualification(@RequestBody AcademicQualificationDto academicQualificationDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();
        academicQualificationService.addAcademicQualification(academicQualificationDto, userid);

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
