package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.ConferenceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConferenceService {
    void addConference(String name, String userid);

    List<ConferenceDto> getAllConferences(String userid);

    void updateConferences(String userid, List<ConferenceDto> ConferenceDtos);

}
