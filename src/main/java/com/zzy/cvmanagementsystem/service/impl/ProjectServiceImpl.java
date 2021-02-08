package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.ProjectDao;
import com.zzy.cvmanagementsystem.dto.ProjectDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.ProjectRepository;
import com.zzy.cvmanagementsystem.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDao> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public void updateProjectById(String id, ProjectDto projectDto) {
        ProjectDao projectDao = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project Not Found"));
        projectDao.setExternal(projectDto.isExternal());
        projectDao.setEndYear(projectDto.getEndYear());
        projectDao.setFunder(projectDto.getFunder());
        projectDao.setFundingAmount(projectDto.getFundingAmount());
        projectDao.setRole(projectDto.getRole());
        projectDao.setStartYear(projectDto.getStartYear());
        projectDao.setTitle(projectDto.getTitle());
        projectRepository.save(projectDao);
    }

    @Override
    public void addProject(ProjectDto projectDto) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setExternal(projectDto.isExternal());
        projectDao.setEndYear(projectDto.getEndYear());
        projectDao.setFunder(projectDto.getFunder());
        projectDao.setFundingAmount(projectDto.getFundingAmount());
        projectDao.setRole(projectDto.getRole());
        projectDao.setStartYear(projectDto.getStartYear());
        projectDao.setTitle(projectDto.getTitle());
        projectRepository.save(projectDao);

    }

    @Override
    public void deleteProjectById(String id) {
        projectRepository.deleteById(id);
    }
}
