package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.AwardDto;
import com.zzy.cvmanagementsystem.service.AwardService;
import com.zzy.cvmanagementsystem.service.UserService;
import com.zzy.cvmanagementsystem.service.impl.AwardServiceImpl;
import com.zzy.cvmanagementsystem.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/awards")
@CrossOrigin
@Slf4j
public class AwardController {

    private AwardService awardService;
    private UserService userService;

    AwardController(AwardServiceImpl awardService, UserServiceImpl userService) {
        this.awardService = awardService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllAwards(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        List<AwardDto> awardDtos = awardService.getAllAwards(userid);

        return ResponseEntity.ok(awardDtos);
    }

    @PostMapping("")
    public ResponseEntity addAward(@RequestBody AwardDto awardDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUserByUsername(username).getId();

        awardService.addAward(awardDto, userid);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{awardId}")
    public ResponseEntity updateAward(@PathVariable String awardId, @RequestBody AwardDto awardDto) {
        awardService.updateAwardById(awardId, awardDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{awardId}")
    public ResponseEntity deleteAward(@PathVariable String awardId) {
        awardService.deleteAward(awardId);

        return ResponseEntity.noContent().build();
    }
}
