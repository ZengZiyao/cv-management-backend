package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.BiographyDao;
import com.zzy.cvmanagementsystem.dto.BiographyDto;
import org.springframework.stereotype.Service;

@Service
public interface BiographyService {

    BiographyDao getBiography();

    void updateBiography(BiographyDto biographyDto);

    void addBiography(BiographyDto biographyDto);

    void deleteBiography();

}
