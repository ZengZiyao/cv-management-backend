package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.WorkExperienceDao;
import com.zzy.cvmanagementsystem.dto.WorkExperienceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkExperienceService {
    List<WorkExperienceDao> getAllWorkExperiences();

    void addWorkExperience(WorkExperienceDto workExperienceDto);

    void updateWorkExperience(String id, WorkExperienceDto workExperienceDto);

    void deleteWorkExperience(String id);
}
