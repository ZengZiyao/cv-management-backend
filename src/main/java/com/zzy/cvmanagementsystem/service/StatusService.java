package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.StatusDto;
import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    StatusDto getStatus(String userid);

    void updateStatus(String userid, String id, StatusDto statusDto);

    void createStatus(String userid);
}
