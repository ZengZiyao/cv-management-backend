package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.AwardDao;
import com.zzy.cvmanagementsystem.dto.AwardDto;
import com.zzy.cvmanagementsystem.service.AwardService;
import com.zzy.cvmanagementsystem.service.impl.AwardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/awards")
@CrossOrigin
@Slf4j
public class AwardController {

    private AwardService awardService;

    AwardController(AwardServiceImpl awardService) {
        this.awardService = awardService;
    }

    @GetMapping("")
    public ResponseEntity getAllAwards() {
        List<AwardDao> awardDaos = awardService.getAllAwards();

        return ResponseEntity.ok(awardDaos);
    }

    @PostMapping("")
    public ResponseEntity addAward(@RequestBody AwardDto awardDto) {
        awardService.addAward(awardDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{awardId}")
    public ResponseEntity updateAward(@PathVariable String awardId, @RequestBody AwardDto awardDto) {
        awardService.updateAwardById(awardId, awardDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{awardId}")
    public ResponseEntity deleteAward(@PathVariable String awardId) {
        awardService.deleteAward(awardId);

        return ResponseEntity.noContent().build();
    }
}
