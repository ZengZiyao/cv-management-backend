package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.ProjectDao;
import com.zzy.cvmanagementsystem.dto.ProjectDto;
import com.zzy.cvmanagementsystem.service.ProjectService;
import com.zzy.cvmanagementsystem.service.impl.ProjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/projects")
@CrossOrigin
@Slf4j
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectDao>> getAllProjects() {
        List<ProjectDao> projectDaos = projectService.getAllProject();
        return ResponseEntity.ok(projectDaos);
    }

    @PostMapping("")
    public ResponseEntity addProject(@RequestBody ProjectDto projectDto) {
        projectService.addProject(projectDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pid}")
    public ResponseEntity updateProject(@PathVariable String pid, @RequestBody ProjectDto projectDto) {
        projectService.updateProjectById(pid, projectDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity deleteProject(@PathVariable String pid) {
        projectService.deleteProjectById(pid);
        return ResponseEntity.noContent().build();
    }
}
