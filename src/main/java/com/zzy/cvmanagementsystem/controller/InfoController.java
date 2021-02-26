package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.InfoDto;
import com.zzy.cvmanagementsystem.service.InfoService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.InfoServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cv/info")
@CrossOrigin
@Slf4j
public class InfoController {
    private InfoService infoService;
    private UserService userService;

    InfoController(InfoServiceImpl infoService, UserServiceImpl userService) {
        this.userService = userService;
        this.infoService = infoService;
    }

    @GetMapping("")
    public ResponseEntity getInfo(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        return ResponseEntity.ok(infoService.getInfo(userid));
    }

    @PostMapping("")
    public ResponseEntity addInfo(@RequestBody InfoDto infoDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        infoService.addInfo(infoDto, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateInfo(@PathVariable String id, @RequestBody InfoDto infoDto) {
        infoService.updateInfo(id, infoDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInfo(@PathVariable String id) {
        infoService.deleteInfo(id);

        return ResponseEntity.noContent().build();
    }
}
