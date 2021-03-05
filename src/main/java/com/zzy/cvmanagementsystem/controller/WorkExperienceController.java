package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.WorkExperienceDto;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.WorkExperienceService;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.WorkExperienceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/work-experiences")
@CrossOrigin
public class WorkExperienceController {
    private WorkExperienceService workExperienceService;
    private UserService userService;

    WorkExperienceController(WorkExperienceServiceImpl workExperienceService, UserServiceImpl userService) {
        this.workExperienceService = workExperienceService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllWorkExperiences(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<WorkExperienceDto> workExperienceDtos = workExperienceService.getAllWorkExperiences(userid);

        return ResponseEntity.ok(workExperienceDtos);
    }

    @PostMapping("")
    public ResponseEntity addWorkExperience(@RequestBody WorkExperienceDto workExperienceDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        workExperienceService.addWorkExperience(workExperienceDto, userid);

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
