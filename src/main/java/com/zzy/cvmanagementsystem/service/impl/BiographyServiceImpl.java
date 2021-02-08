package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.BiographyDao;
import com.zzy.cvmanagementsystem.dto.BiographyDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.BiographyRepository;
import com.zzy.cvmanagementsystem.service.BiographyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiographyServiceImpl implements BiographyService {

    private BiographyRepository biographyRepository;

    BiographyServiceImpl(BiographyRepository biographyRepository) {
        this.biographyRepository = biographyRepository;
    }

    @Override
    public BiographyDao getBiography() {
        List<BiographyDao> biographyDaoList = biographyRepository.findAll();

        if (!biographyDaoList.isEmpty()) {
            return biographyDaoList.get(0);

        }

        throw new NotFoundException("Biography not found");

    }

    @Override
    public void updateBiography(BiographyDto biographyDto) {
        BiographyDao biographyDao = biographyRepository.findAll().get(0);
        biographyDao.setContent(biographyDto.getContent());
        biographyRepository.save(biographyDao);
    }

    @Override
    public void addBiography(BiographyDto biographyDto) {
        biographyRepository.deleteAll();
        BiographyDao biographyDao = new BiographyDao();
        biographyDao.setContent(biographyDto.getContent());

        biographyRepository.deleteAll();
        biographyRepository.save(biographyDao);
    }

    @Override
    public void deleteBiography() {
        biographyRepository.deleteAll();
    }
}
