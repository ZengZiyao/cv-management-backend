package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.WorkExperienceDao;
import com.zzy.cvmanagementsystem.dto.WorkExperienceDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.WorkExperienceRepository;
import com.zzy.cvmanagementsystem.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private WorkExperienceRepository workExperienceRepository;

    WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    @Override
    public List<WorkExperienceDto> getAllWorkExperiences(String userid) {
        List<WorkExperienceDto> workExperienceDtoList = new ArrayList<>();
        List<WorkExperienceDao> workExperienceDaoList = workExperienceRepository.findAllByUserId(userid);
        for (WorkExperienceDao workExperienceDao : workExperienceDaoList) {
            WorkExperienceDto workExperienceDto = new WorkExperienceDto();
            workExperienceDto.setCompany(workExperienceDao.getCompany());
            workExperienceDto.setEndTime(workExperienceDao.getEndTime());
            workExperienceDto.setStartTime(workExperienceDao.getStartTime());
            workExperienceDto.setTitle(workExperienceDao.getTitle());
            workExperienceDto.setId(workExperienceDao.getId());
            workExperienceDtoList.add(workExperienceDto);
        }
        return workExperienceDtoList;
    }

    @Override
    public void addWorkExperience(WorkExperienceDto workExperienceDto, String userid) {
        WorkExperienceDao workExperienceDao = new WorkExperienceDao();
        workExperienceDao.setCompany(workExperienceDto.getCompany());
        workExperienceDao.setEndTime(workExperienceDto.getEndTime());
        workExperienceDao.setStartTime(workExperienceDto.getStartTime());
        workExperienceDao.setTitle(workExperienceDto.getTitle());
        workExperienceDao.setUserId(userid);
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

    @Override
    public void deleteByUserId(String userid) {
        workExperienceRepository.deleteAll(workExperienceRepository.findAllByUserId(userid));
    }
}
