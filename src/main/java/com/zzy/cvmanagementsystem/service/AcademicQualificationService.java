package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.AcademicQualificationDao;
import com.zzy.cvmanagementsystem.dto.AcademicQualificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AcademicQualificationService {
    List<AcademicQualificationDao> getAllAcademicQualifications();

    void updateAcademicQualificationById(String id, AcademicQualificationDto academicQualificationDto);

    void addAcademicQualification(AcademicQualificationDto academicQualificationDto);

    void deleteAcademicQualification(String id);

}
