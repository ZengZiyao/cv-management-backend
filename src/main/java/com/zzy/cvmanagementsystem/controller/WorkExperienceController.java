package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.WorkExperienceDao;
import com.zzy.cvmanagementsystem.dto.WorkExperienceDto;
import com.zzy.cvmanagementsystem.service.WorkExperienceService;
import com.zzy.cvmanagementsystem.service.impl.WorkExperienceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/work-experiences")
@CrossOrigin
public class WorkExperienceController {
    private WorkExperienceService workExperienceService;

    WorkExperienceController(WorkExperienceServiceImpl workExperienceService) {
        this.workExperienceService = workExperienceService;
    }

    @GetMapping("")
    public ResponseEntity getAllWorkExperiences() {
        List<WorkExperienceDao> workExperienceDaos = workExperienceService.getAllWorkExperiences();

        return ResponseEntity.ok(workExperienceDaos);
    }

    @PostMapping("")
    public ResponseEntity addWorkExperience(@RequestBody WorkExperienceDto workExperienceDto) {
        workExperienceService.addWorkExperience(workExperienceDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{wId}")
    public ResponseEntity updateWorkExperience(@PathVariable String wId, @RequestBody WorkExperienceDto workExperienceDto) {
        workExperienceService.updateWorkExperience(wId, workExperienceDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{wId}")
    public ResponseEntity deleteWorkExperience(@PathVariable String wId) {
        workExperienceService.deleteWorkExperience(wId);

        return ResponseEntity.noContent().build();
    }
}
