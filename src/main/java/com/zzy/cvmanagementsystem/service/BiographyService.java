package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.BiographyDto;
import org.springframework.stereotype.Service;

@Service
public interface BiographyService {

    BiographyDto getBiography(String userid);

    void updateBiography(String id, BiographyDto biographyDto);

    void addBiography(BiographyDto biographyDto, String userid);

    void deleteBiography(String id);

}
