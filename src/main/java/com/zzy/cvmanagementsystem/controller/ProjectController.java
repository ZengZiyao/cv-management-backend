package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.ProjectDto;
import com.zzy.cvmanagementsystem.service.ProjectService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.ProjectServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/projects")
@CrossOrigin
@Slf4j
public class ProjectController {
    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectServiceImpl projectService, UserServiceImpl userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectDto>> getAllProjects(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<ProjectDto> projectDtos = projectService.getAllProject(userid);
        return ResponseEntity.ok(projectDtos);
    }

    @PostMapping("")
    public ResponseEntity addProject(@RequestBody ProjectDto projectDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        projectService.addProject(projectDto, userid);
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
