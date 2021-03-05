package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.ConnectionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ConnectionService {
    Map<String, Map<String, List<ConnectionDto>>> getAllConnections(String username);

    void acceptConnection(String id);

    void rejectConnection(String id);

    void requestConnection(String username, String followerUsername);

    void cancelRequest(String id);

    void removeConnection(String id);
}
