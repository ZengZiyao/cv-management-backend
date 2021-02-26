package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.AwardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AwardService {

    List<AwardDto> getAllAwards(String userid);

    void updateAwardById(String id, AwardDto awardDto);

    void addAward(AwardDto awardDto, String userId);

    void deleteAward(String id);

    void deleteByUserId(String userid);
}
