package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.InfoDao;
import com.zzy.cvmanagementsystem.dto.InfoDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.InfoRepository;
import com.zzy.cvmanagementsystem.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InfoServiceImpl implements InfoService {

    private InfoRepository infoRepository;

    InfoServiceImpl(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public void addInfo(InfoDto infoDto) {
        infoRepository.deleteAll();
        InfoDao infoDao = new InfoDao();
        infoDao.setDesignation(infoDto.getDesignation());
        infoDao.setEmail(infoDto.getEmail());
        infoDao.setName(infoDto.getName());

        infoRepository.save(infoDao);

    }

    @Override
    public void updateInfo(InfoDto infoDto) {
        InfoDao infoDao = infoRepository.findAll().get(0);

        infoDao.setName(infoDto.getName());
        infoDao.setEmail(infoDto.getEmail());
        infoDao.setDesignation(infoDto.getDesignation());

        infoRepository.save(infoDao);
    }

    @Override
    public InfoDao getInfo() {

        List<InfoDao> infoDaos = infoRepository.findAll();

        if (!infoDaos.isEmpty()) {
            return infoDaos.get(0);
        }
        throw new NotFoundException("Info not found");
    }

    @Override
    public void deleteInfo() {
        infoRepository.deleteAll();
    }
}
