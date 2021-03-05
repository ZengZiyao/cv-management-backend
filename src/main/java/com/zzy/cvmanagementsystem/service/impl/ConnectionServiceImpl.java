package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.ConnectionDao;
import com.zzy.cvmanagementsystem.dao.UserDao;
import com.zzy.cvmanagementsystem.dto.ConnectionDto;
import com.zzy.cvmanagementsystem.dto.UserDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.model.ConnectionStatus;
import com.zzy.cvmanagementsystem.repository.ConnectionRepository;
import com.zzy.cvmanagementsystem.service.ConnectionService;
import com.zzy.cvmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {
    private ConnectionRepository connectionRepository;
    private UserService userService;

    ConnectionServiceImpl(ConnectionRepository connectionRepository, UserServiceImpl userService) {
        this.connectionRepository = connectionRepository;
        this.userService = userService;
    }

    @Override
    public Map<String, Map<String, List<ConnectionDto>>> getAllConnections(String username) {
        String userid = userService.getUserByUsername(username).getId();
        Map<String, Map<String, List<ConnectionDto>>> connectionMap = new HashMap<>();
        ConnectionDao connectionDao = new ConnectionDao();
        connectionDao.setUserId(userid);
        List<ConnectionDao> connectionDaoList = connectionRepository.findAll(Example.of(connectionDao));
        Map<String, List<ConnectionDto>> followerMap = new HashMap<>();
        List<ConnectionDto> acceptDtoList = new ArrayList<>();
        List<ConnectionDto> connectionDtoList = new ArrayList<>();
        for (ConnectionDao dao : connectionDaoList) {
            UserDao userDao = userService.getUserById(dao.getFollowerId());
            if (userDao != null) {
                ConnectionDto dto = new ConnectionDto(dao.getId(), username, userDao.getUsername(), dao.getConnectionStatus(), dao.getUpdateTime());
                if (dao.getConnectionStatus() == ConnectionStatus.ACCEPTED) {
                    acceptDtoList.add(dto);
                }

                connectionDtoList.add(dto);
            }
        }
        connectionDtoList.sort((a, b) -> (int) (b.getUpdateTime().getTime() - a.getUpdateTime().getTime()));

        followerMap.put("accept", acceptDtoList);
        followerMap.put("all", connectionDtoList);
        connectionMap.put("follower", followerMap);

        ConnectionDao fconnectionDao = new ConnectionDao();
        fconnectionDao.setFollowerId(userid);
        List<ConnectionDao> fconnectionDaoList = connectionRepository.findAll(Example.of(fconnectionDao));
        Map<String, List<ConnectionDto>> followingMap = new HashMap<>();
        List<ConnectionDto> facceptDtoList = new ArrayList<>();
        List<ConnectionDto> fconnectionDtoList = new ArrayList<>();
        for (ConnectionDao dao : fconnectionDaoList) {
            UserDao userDao = userService.getUserById(dao.getUserId());
            if (userDao != null) {
                ConnectionDto dto = new ConnectionDto(dao.getId(), userDao.getUsername(), username, dao.getConnectionStatus(), dao.getUpdateTime());
                if (dao.getConnectionStatus() == ConnectionStatus.ACCEPTED) {
                    facceptDtoList.add(dto);

                }
                fconnectionDtoList.add(dto);
            }
        }
        fconnectionDtoList.sort((a, b) -> (int) (b.getUpdateTime().getTime() - a.getUpdateTime().getTime()));

        followingMap.put("accept", facceptDtoList);
        followingMap.put("all", fconnectionDtoList);
        connectionMap.put("following", followingMap);

        return connectionMap;
    }

    @Override
    public void acceptConnection(String id) {
        ConnectionDao connectionDao = connectionRepository.findById(id).orElseThrow(() -> new NotFoundException("connection not found"));
        connectionDao.setConnectionStatus(ConnectionStatus.ACCEPTED);
        connectionDao.setUpdateTime(new Date());
        connectionRepository.save(connectionDao);
    }

    @Override
    public void rejectConnection(String id) {
        ConnectionDao connectionDao = connectionRepository.findById(id).orElseThrow(() -> new NotFoundException("connection not found"));
        connectionDao.setConnectionStatus(ConnectionStatus.REJECTED);
        connectionDao.setUpdateTime(new Date());
        connectionRepository.save(connectionDao);
    }

    @Override
    public void requestConnection(String username, String followerUsername) {
        log.info(username);
        log.info(followerUsername);
        UserDto userDto = userService.getUserByUsername(username);
        UserDto follower = userService.getUserByUsername(followerUsername);
        ConnectionDao connectionDao = new ConnectionDao();
        connectionDao.setUpdateTime(new Date());
        connectionDao.setConnectionStatus(ConnectionStatus.PENDING);
        connectionDao.setFollowerId(follower.getId());
        connectionDao.setUserId(userDto.getId());
        connectionRepository.save(connectionDao);

    }

    @Override
    public void cancelRequest(String id) {
        ConnectionDao connectionDao = connectionRepository.findById(id).orElseThrow(() -> new NotFoundException("connection not found"));
        connectionDao.setConnectionStatus(ConnectionStatus.CANCELLED);
        connectionDao.setUpdateTime(new Date());
        connectionRepository.save(connectionDao);

    }


    @Override
    public void removeConnection(String id) {
        connectionRepository.deleteById(id);
    }
}
