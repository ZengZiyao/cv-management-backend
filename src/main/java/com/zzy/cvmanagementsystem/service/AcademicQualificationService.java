package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.AcademicQualificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicQualificationService {
    List<AcademicQualificationDto> getAllAcademicQualifications(String userid);

    void updateAcademicQualificationById(String id, AcademicQualificationDto academicQualificationDto);

    void addAcademicQualification(AcademicQualificationDto academicQualificationDto, String userId);

    void deleteAcademicQualification(String id);

    void deleteByUserId(String userid);

}
