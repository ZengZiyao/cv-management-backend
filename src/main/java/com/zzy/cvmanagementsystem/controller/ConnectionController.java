package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.service.ConnectionService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.ConnectionServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/connections")
@CrossOrigin
@Slf4j
public class ConnectionController {
    private ConnectionService connectionService;
    private UserService userService;

    ConnectionController(ConnectionServiceImpl connectionService, UserServiceImpl userService) {
        this.connectionService = connectionService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllConnections(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        return ResponseEntity.ok(connectionService.getAllConnections(username));
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity acceptConnection(@PathVariable String id) {
        connectionService.acceptConnection(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity rejectConnection(@PathVariable String id) {
        connectionService.rejectConnection(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity requestConnection(@RequestBody String username, HttpServletRequest httpServletRequest) {
        String followerUsername = httpServletRequest.getUserPrincipal().getName();

        connectionService.requestConnection(username, followerUsername);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity cancelRequest(@PathVariable String id) {
        connectionService.cancelRequest(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeConnection(@PathVariable String id) {
        log.info(id);
        connectionService.removeConnection(id);
        return ResponseEntity.noContent().build();
    }
}
