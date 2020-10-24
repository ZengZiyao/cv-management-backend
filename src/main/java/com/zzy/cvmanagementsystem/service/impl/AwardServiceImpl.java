package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.AwardDao;
import com.zzy.cvmanagementsystem.dto.AwardDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.AwardRepository;
import com.zzy.cvmanagementsystem.service.AwardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private AwardRepository awardRepository;

    AwardServiceImpl(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @Override
    public List<AwardDao> getAllAwards() {
        return awardRepository.findAll();
    }

    @Override
    public void updateAwardById(String id, AwardDto awardDto) {
        AwardDao awardDao = awardRepository.findById(id).orElseThrow(() -> new NotFoundException("Award Not Found"));

        awardDao.setContent(awardDto.getContent());

        awardDao.setDate(awardDto.getDate());

        awardRepository.save(awardDao);
    }

    @Override
    public void addAward(AwardDto awardDto) {
        AwardDao awardDao = new AwardDao();
        awardDao.setDate(awardDao.getDate());
        awardDao.setContent(awardDto.getContent());
        awardRepository.save(awardDao);
    }

    @Override
    public void deleteAward(String id) {
        awardRepository.deleteById(id);
    }
}
