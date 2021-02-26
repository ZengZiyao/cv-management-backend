package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.AcademicQualificationDao;
import com.zzy.cvmanagementsystem.dto.AcademicQualificationDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.AcademicQualificationRepository;
import com.zzy.cvmanagementsystem.service.AcademicQualificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicQualificationServiceImpl implements AcademicQualificationService {
    private AcademicQualificationRepository academicQualificationRepository;

    AcademicQualificationServiceImpl(AcademicQualificationRepository academicQualificationRepository) {
        this.academicQualificationRepository = academicQualificationRepository;
    }

    @Override
    public List<AcademicQualificationDto> getAllAcademicQualifications(String userid) {
        List<AcademicQualificationDto> academicQualificationDtoList = new ArrayList<>();
        List<AcademicQualificationDao> academicQualificationDaoList = academicQualificationRepository.findAllByUserId(userid);
        for (AcademicQualificationDao academicQualificationDao : academicQualificationDaoList) {
            AcademicQualificationDto academicQualificationDto = new AcademicQualificationDto();
            academicQualificationDto.setDegree(academicQualificationDao.getDegree());
            academicQualificationDto.setId(academicQualificationDao.getId());
            academicQualificationDto.setMajor(academicQualificationDao.getMajor());
            academicQualificationDto.setUniversity(academicQualificationDao.getUniversity());
            academicQualificationDto.setYear(academicQualificationDao.getYear());
            academicQualificationDto.setId(academicQualificationDao.getId());
            academicQualificationDtoList.add(academicQualificationDto);
        }
        return academicQualificationDtoList;
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
    public void addAcademicQualification(AcademicQualificationDto academicQualificationDto, String userid) {
        AcademicQualificationDao academicQualificationDao = new AcademicQualificationDao();
        academicQualificationDao.setDegree(academicQualificationDto.getDegree());
        academicQualificationDao.setMajor(academicQualificationDto.getMajor());
        academicQualificationDao.setUniversity(academicQualificationDto.getUniversity());
        academicQualificationDao.setYear(academicQualificationDto.getYear());
        academicQualificationDao.setUserId(userid);
        academicQualificationRepository.save(academicQualificationDao);
    }

    @Override
    public void deleteAcademicQualification(String id) {
        academicQualificationRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(String userid) {
        academicQualificationRepository.deleteAll(academicQualificationRepository.findAllByUserId(userid));
    }
}
