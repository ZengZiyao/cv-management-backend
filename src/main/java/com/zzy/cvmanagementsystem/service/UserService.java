package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.Credential;
import com.zzy.cvmanagementsystem.dto.UserDto;

public interface UserService {
    void signUp(UserDto userDto);

    String signIn(Credential credential);

    UserDto getUser(String username);

    void updateUser(UserDto userDto);

    void updatePassword(String userid, String password);
}
