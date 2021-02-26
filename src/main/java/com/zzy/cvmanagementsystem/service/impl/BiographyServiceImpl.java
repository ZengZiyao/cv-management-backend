package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.BiographyDao;
import com.zzy.cvmanagementsystem.dto.BiographyDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.BiographyRepository;
import com.zzy.cvmanagementsystem.service.BiographyService;
import org.springframework.stereotype.Service;

@Service
public class BiographyServiceImpl implements BiographyService {

    private BiographyRepository biographyRepository;

    BiographyServiceImpl(BiographyRepository biographyRepository) {
        this.biographyRepository = biographyRepository;
    }

    @Override
    public BiographyDto getBiography(String userid) {
        BiographyDao biographyDao = biographyRepository.findByUserId(userid);

        BiographyDto biographyDto = new BiographyDto();
        biographyDto.setContent(biographyDao.getContent());
        biographyDto.setId(biographyDao.getId());
        return biographyDto;


    }

    @Override
    public void updateBiography(String id, BiographyDto biographyDto) {
        BiographyDao biographyDao = biographyRepository.findById(id).orElseThrow(() -> new NotFoundException("Biography not found"));
        biographyDao.setContent(biographyDto.getContent());
        biographyRepository.save(biographyDao);
    }

    @Override
    public void addBiography(BiographyDto biographyDto, String userid) {
        BiographyDao biographyDao = new BiographyDao();
        biographyDao.setContent(biographyDto.getContent());
        biographyDao.setUserId(userid);
        biographyRepository.save(biographyDao);
    }

    @Override
    public void deleteBiography(String id) {
        biographyRepository.deleteById(id);
    }
}
