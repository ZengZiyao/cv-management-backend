package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.UserDao;
import com.zzy.cvmanagementsystem.dto.Credential;
import com.zzy.cvmanagementsystem.dto.UserDto;

import java.util.List;

public interface UserService {
    void signUp(UserDto userDto);

    String signIn(Credential credential);

    UserDto getUserByUsername(String username);

    UserDao getUserById(String id);

    void updateUser(UserDto userDto);

    void updatePassword(String userid, String password);

    List<UserDto> getAllUsersWithGS();
}
