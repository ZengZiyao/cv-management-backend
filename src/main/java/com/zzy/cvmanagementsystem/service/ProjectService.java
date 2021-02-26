package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectDto> getAllProject(String userid);

    void updateProjectById(String id, ProjectDto projectDto);

    void addProject(ProjectDto projectDto, String userid);

    void deleteProjectById(String id);

    void deleteByUserId(String userid);

}
