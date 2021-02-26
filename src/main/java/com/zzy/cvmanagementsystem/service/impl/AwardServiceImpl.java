package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.AwardDao;
import com.zzy.cvmanagementsystem.dto.AwardDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.AwardRepository;
import com.zzy.cvmanagementsystem.service.AwardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private AwardRepository awardRepository;

    AwardServiceImpl(AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
    }

    @Override
    public List<AwardDto> getAllAwards(String userid) {
        List<AwardDto> awardDtos = new ArrayList<>();
        List<AwardDao> awardDaos = awardRepository.findAllByUserId(userid);
        for (AwardDao awardDao : awardDaos) {
            AwardDto awardDto = new AwardDto();
            awardDto.setContent(awardDao.getContent());
            awardDto.setDate(awardDao.getDate());
            awardDto.setId(awardDao.getId());

            awardDtos.add(awardDto);
        }

        return awardDtos;
    }

    @Override
    public void updateAwardById(String id, AwardDto awardDto) {
        AwardDao awardDao = awardRepository.findById(id).orElseThrow(() -> new NotFoundException("Award Not Found"));

        awardDao.setContent(awardDto.getContent());

        awardDao.setDate(awardDto.getDate());

        awardRepository.save(awardDao);
    }

    @Override
    public void addAward(AwardDto awardDto, String userid) {
        AwardDao awardDao = new AwardDao();
        awardDao.setDate(awardDto.getDate());
        awardDao.setContent(awardDto.getContent());
        awardDao.setUserId(userid);
        awardRepository.save(awardDao);
    }

    @Override
    public void deleteAward(String id) {
        awardRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(String userid) {
        awardRepository.deleteAll(awardRepository.findAllByUserId(userid));
    }
}
