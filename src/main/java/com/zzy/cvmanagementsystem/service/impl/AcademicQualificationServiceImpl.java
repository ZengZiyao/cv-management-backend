package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.AcademicQualificationDao;
import com.zzy.cvmanagementsystem.dto.AcademicQualificationDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.AcademicQualificationRepository;
import com.zzy.cvmanagementsystem.service.AcademicQualificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicQualificationServiceImpl implements AcademicQualificationService {
    private AcademicQualificationRepository academicQualificationRepository;

    AcademicQualificationServiceImpl(AcademicQualificationRepository academicQualificationRepository) {
        this.academicQualificationRepository = academicQualificationRepository;
    }

    @Override
    public List<AcademicQualificationDao> getAllAcademicQualifications() {
        return academicQualificationRepository.findAll();
    }

    @Override
    public void updateAcademicQualificationById(String id, AcademicQualificationDto academicQualificationDto) {
        AcademicQualificationDao academicQualificationDao = academicQualificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Academic Qualification not found"));
        academicQualificationDao.setDegree(academicQualificationDto.getDegree());
        academicQualificationDao.setMajor(academicQualificationDto.getMajor());
        academicQualificationDao.setUniversity(academicQualificationDto.getUniversity());
        academicQualificationDao.setYear(academicQualificationDto.getYear());
        academicQualificationRepository.save(academicQualificationDao);
    }

    @Override
    public void addAcademicQualification(AcademicQualificationDto academicQualificationDto) {
        AcademicQualificationDao academicQualificationDao = new AcademicQualificationDao();
        academicQualificationDao.setDegree(academicQualificationDto.getDegree());
        academicQualificationDao.setMajor(academicQualificationDto.getMajor());
        academicQualificationDao.setUniversity(academicQualificationDto.getUniversity());
        academicQualificationDao.setYear(academicQualificationDto.getYear());
        academicQualificationRepository.save(academicQualificationDao);
    }

    @Override
    public void deleteAcademicQualification(String id) {
        academicQualificationRepository.deleteById(id);
    }
}
