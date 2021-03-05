package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.common.utils.JwtTokenUtil;
import com.zzy.cvmanagementsystem.dto.Credential;
import com.zzy.cvmanagementsystem.dto.UserDto;
import com.zzy.cvmanagementsystem.exception.BadRequestException;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    private UserService userService;
    private JwtTokenUtil jwtTokenUtil;

    public UserController(UserServiceImpl userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getPassword() == null) {
            throw new BadRequestException("Bad attribute values");
        }
        userService.signUp(userDto);
        Credential credential = new Credential(userDto.getUsername(), userDto.getPassword());
        String token = userService.signIn(credential);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResponseEntity.ok(tokenMap);
    }

    @PostMapping("/login")
    public ResponseEntity signin(@RequestBody Credential credential) {

        if (credential.getUsername() == null || credential.getPassword() == null) {
            throw new BadRequestException("Bad attribute values");
        }
        String token = userService.signIn(credential);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResponseEntity.ok(tokenMap);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        jwtTokenUtil.invalidateToken(jwtTokenUtil.getTokenFromRequest(httpServletRequest));

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUser(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/profile")
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/profile/password")
    public ResponseEntity updatePassword(@RequestBody String password, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        userService.updatePassword(userid, password);
        return ResponseEntity.noContent().build();
    }
}
