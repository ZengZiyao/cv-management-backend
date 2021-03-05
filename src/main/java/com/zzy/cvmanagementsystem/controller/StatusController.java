package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.StatusDto;
import com.zzy.cvmanagementsystem.service.StatusService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.StatusServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cv/status")
@CrossOrigin
public class StatusController {
    private StatusService statusService;
    private UserService userService;

    StatusController(StatusServiceImpl statusService, UserServiceImpl userService) {
        this.statusService = statusService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getStatus(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        StatusDto statusDto = statusService.getStatus(userid);

        return ResponseEntity.ok(statusDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStatus(@PathVariable String id, @RequestBody StatusDto statusDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        statusService.updateStatus(userid, id, statusDto);
        return ResponseEntity.noContent().build();
    }
}
