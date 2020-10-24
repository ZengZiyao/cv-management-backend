package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.WorkExperienceDao;
import com.zzy.cvmanagementsystem.dto.WorkExperienceDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.WorkExperienceRepository;
import com.zzy.cvmanagementsystem.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private WorkExperienceRepository workExperienceRepository;

    WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    @Override
    public List<WorkExperienceDao> getAllWorkExperiences() {
        return workExperienceRepository.findAll();
    }

    @Override
    public void addWorkExperience(WorkExperienceDto workExperienceDto) {
        WorkExperienceDao workExperienceDao = new WorkExperienceDao();
        workExperienceDao.setCompany(workExperienceDto.getCompany());
        workExperienceDao.setEndTime(workExperienceDto.getEndTime());
        workExperienceDao.setStartTime(workExperienceDto.getStartTime());
        workExperienceDao.setTitle(workExperienceDto.getTitle());

        workExperienceRepository.save(workExperienceDao);
    }

    @Override
    public void updateWorkExperience(String id, WorkExperienceDto workExperienceDto) {

        WorkExperienceDao workExperienceDao = workExperienceRepository.findById(id).orElseThrow(() -> new NotFoundException("Work e xperience not found"));
        workExperienceDao.setCompany(workExperienceDto.getCompany());
        workExperienceDao.setEndTime(workExperienceDto.getEndTime());
        workExperienceDao.setStartTime(workExperienceDto.getStartTime());
        workExperienceDao.setTitle(workExperienceDto.getTitle());

        workExperienceRepository.save(workExperienceDao);

    }

    @Override
    public void deleteWorkExperience(String id) {
        workExperienceRepository.deleteById(id);
    }
}
