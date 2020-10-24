package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.InfoDao;
import com.zzy.cvmanagementsystem.dto.InfoDto;
import org.springframework.stereotype.Service;

@Service
public interface InfoService {
    void addInfo(InfoDto infoDto);

    void updateInfo(InfoDto infoDto);

    InfoDao getInfo();

    void deleteInfo();

}
