package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.InfoDto;
import com.zzy.cvmanagementsystem.service.InfoService;
import com.zzy.cvmanagementsystem.service.impl.InfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cv/info")
@CrossOrigin
@Slf4j
public class InfoController {
    private InfoService infoService;

    InfoController(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }

    @GetMapping("")
    public ResponseEntity getInfo() {

        return ResponseEntity.ok(infoService.getInfo());
    }

    @PostMapping("")
    public ResponseEntity addInfo(@RequestBody InfoDto infoDto) {
        infoService.addInfo(infoDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("")
    public ResponseEntity updateInfo(@RequestBody InfoDto infoDto) {
        infoService.updateInfo(infoDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity deleteInfo() {
        infoService.deleteInfo();

        return ResponseEntity.noContent().build();
    }
}
