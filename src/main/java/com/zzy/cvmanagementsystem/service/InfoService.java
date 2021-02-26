package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.InfoDto;
import org.springframework.stereotype.Service;

@Service
public interface InfoService {
    void addInfo(InfoDto infoDto, String userid);

    void updateInfo(String id, InfoDto infoDto);

    InfoDto getInfo(String userid);

    void deleteInfo(String id);

    void deleteByUserId(String userid);

}
