package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.ProjectDao;
import com.zzy.cvmanagementsystem.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectDao> getAllProject();

    void updateProjectById(String id, ProjectDto projectDto);

    void addProject(ProjectDto projectDto);

    void deleteProjectById(String id);
}
