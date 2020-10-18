package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.CvDao;
import com.zzy.cvmanagementsystem.dto.CvDto;
import com.zzy.cvmanagementsystem.dto.CvResponse;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.CvRepository;
import com.zzy.cvmanagementsystem.service.CvService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CvServiceImpl implements CvService {
    private final CvRepository cvRepository;

    public CvServiceImpl(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Override
    public CvResponse getCvById(String cvId) {
        CvDao cvDao = cvRepository.findById(cvId).orElseThrow(() -> new NotFoundException("CV not found: " + cvId));

        return CvResponse.fromDao(cvDao);
    }

    @Override
    public String postCv(CvDto cvDto) {
        CvDao cvDao = CvDao.fromDto(cvDto);

        cvRepository.save(cvDao);

        return cvDao.getId();
    }

    @Override
    public void deleteCv(String cvId) {
        cvRepository.deleteById(cvId);
    }

    @Override
    @Transactional
    public void updateCv(String cvId, Map<String, Object> updates) {
        CvDao cvDao = cvRepository.findById(cvId).orElseThrow(() -> new NotFoundException("CV not found: " + cvId));

        cvDao.set(updates);

        cvRepository.save(cvDao);
    }

}
