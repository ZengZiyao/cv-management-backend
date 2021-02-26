package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.WorkExperienceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkExperienceService {
    List<WorkExperienceDto> getAllWorkExperiences(String userid);

    void addWorkExperience(WorkExperienceDto workExperienceDto, String userid);

    void updateWorkExperience(String id, WorkExperienceDto workExperienceDto);

    void deleteWorkExperience(String id);

    void deleteByUserId(String userid);

}
