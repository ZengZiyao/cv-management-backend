package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.CvDto;
import com.zzy.cvmanagementsystem.dto.CvResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CvService {

    CvResponse getCvById(String cvId);

    String postCv(CvDto cvDto);

    void deleteCv(String cvId);

    void updateCv(String cvId, Map<String, Object> updates);
}
