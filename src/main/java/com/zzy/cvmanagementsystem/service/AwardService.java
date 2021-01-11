package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.AwardDao;
import com.zzy.cvmanagementsystem.dto.AwardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AwardService {

    List<AwardDao> getAllAwards();

    void updateAwardById(String id, AwardDto awardDto);

    void addAward(AwardDto awardDto);

    void deleteAward(String id);
}
