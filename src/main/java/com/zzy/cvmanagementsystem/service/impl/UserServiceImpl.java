package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.common.utils.JwtTokenUtil;
import com.zzy.cvmanagementsystem.dao.UserDao;
import com.zzy.cvmanagementsystem.dto.Credential;
import com.zzy.cvmanagementsystem.dto.UserDto;
import com.zzy.cvmanagementsystem.exception.DuplicateException;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.UserRepository;
import com.zzy.cvmanagementsystem.service.StatusService;
import com.zzy.cvmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
    private StatusService statusService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService, StatusServiceImpl statusService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.statusService = statusService;
    }

    @Override
    public void signUp(UserDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new DuplicateException("username already exists: " + userDto.getUsername());
        } else {
            UserDao userDao = new UserDao();
            userDao.setUsername(userDto.getUsername());
            userDao.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDao.setEmail(userDto.getEmail());
            userDao.setShortname(userDto.getShortname());
            userDao = userRepository.save(userDao);

            statusService.createStatus(userDao.getId());
        }

    }

    @Override
    public String signIn(Credential credential) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(credential.getUsername());
        if (!passwordEncoder.matches(credential.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("username or password does not match");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return jwtTokenUtil.generateToken(userDetails);

    }

    @Override
    public UserDto getUser(String username) {
        UserDao userDao = userRepository.findByUsername(username);
        if (userDao != null) {
            UserDto userDto = new UserDto();
            userDto.setId(userDao.getId());
            userDto.setUsername(userDao.getUsername());
            userDto.setEmail(userDao.getEmail());
            userDto.setPassword(userDao.getPassword());
            userDto.setShortname(userDao.getShortname());
            return userDto;
        }

        throw new NotFoundException("user not found");
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserDao userDao = userRepository.findById(userDto.getId()).orElseThrow(() -> new NotFoundException("user not found"));
        userDao.setEmail(userDto.getEmail());
        userDao.setShortname(userDto.getShortname());
        userRepository.save(userDao);
    }

    @Override
    public void updatePassword(String userid, String password) {
        UserDao userDao = userRepository.findById(userid).orElseThrow(() -> new NotFoundException("user not found"));
        userDao.setPassword(passwordEncoder.encode(password));
        userRepository.save(userDao);
    }
}
