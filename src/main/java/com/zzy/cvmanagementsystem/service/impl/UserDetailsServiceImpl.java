package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.UserDao;
import com.zzy.cvmanagementsystem.model.AppUserDetails;
import com.zzy.cvmanagementsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDao userDao = userRepository.findByUsername(s);
        if (userDao != null) {
            return new AppUserDetails(userDao.getUsername(), userDao.getPassword());
        }

        throw new UsernameNotFoundException(String.format("Username not found: %s", s));
    }

    public String getUserIdByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).getId();
    }
}
