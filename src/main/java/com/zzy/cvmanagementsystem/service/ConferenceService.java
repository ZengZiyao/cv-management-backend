package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.ConferenceDao;
import com.zzy.cvmanagementsystem.dto.ConferenceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConferenceService {
    void addConference(String name);

    List<ConferenceDao> getAllConferences();

    void updateConferences(List<ConferenceDto> ConferenceDtos);

}
