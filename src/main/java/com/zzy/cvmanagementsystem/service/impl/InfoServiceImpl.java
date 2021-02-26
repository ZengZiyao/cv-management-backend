package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.InfoDao;
import com.zzy.cvmanagementsystem.dto.InfoDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.InfoRepository;
import com.zzy.cvmanagementsystem.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InfoServiceImpl implements InfoService {

    private InfoRepository infoRepository;

    InfoServiceImpl(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public void addInfo(InfoDto infoDto, String userid) {
        InfoDao infoDao = new InfoDao();
        infoDao.setDesignation(infoDto.getDesignation());
        infoDao.setSchool(infoDto.getSchool());
        infoDao.setName(infoDto.getName());
        infoDao.setUserId(userid);
        infoRepository.save(infoDao);

    }

    @Override
    public void updateInfo(String id, InfoDto infoDto) {
        InfoDao infoDao = infoRepository.findById(id).orElseThrow(() -> new NotFoundException("Info not found"));

        infoDao.setName(infoDto.getName());
        infoDao.setSchool(infoDto.getSchool());
        infoDao.setDesignation(infoDto.getDesignation());

        infoRepository.save(infoDao);
    }

    @Override
    public InfoDto getInfo(String userid) {

        InfoDao infoDao = infoRepository.findByUserId(userid);
        if (infoDao != null) {
            InfoDto infoDto = new InfoDto();
            infoDto.setDesignation(infoDao.getDesignation());
            infoDto.setName(infoDao.getName());
            infoDto.setSchool(infoDao.getSchool());
            infoDto.setId((infoDao.getId()));
            return infoDto;
        }

        throw new NotFoundException("Info not found");
    }

    @Override
    public void deleteInfo(String id) {
        infoRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(String userid) {
        infoRepository.delete(infoRepository.findByUserId(userid));
    }
}
