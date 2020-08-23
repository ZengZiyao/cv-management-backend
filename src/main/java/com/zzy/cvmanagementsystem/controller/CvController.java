package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.CvDto;
import com.zzy.cvmanagementsystem.dto.CvResponse;
import com.zzy.cvmanagementsystem.service.CvService;
import com.zzy.cvmanagementsystem.service.impl.CvServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cv")
public class CvController {

    private final CvService cvService;

    public CvController(CvServiceImpl cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/{cvId}")
    public ResponseEntity getCvById(@PathVariable String cvId) {
        CvResponse cvResponse = cvService.getCvById(cvId);
        return ResponseEntity.ok(cvResponse);
    }

    @PostMapping("")
    public ResponseEntity postCv(@RequestBody CvDto cvDto) {
        String cvId = cvService.postCv(cvDto);

        Map<String, String> map = new HashMap<>();
        map.put("cvId", cvId);

        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cvId}")
    public ResponseEntity deleteCvById(@PathVariable String cvId) {
        cvService.deleteCv(cvId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{cvId}")
    public ResponseEntity patchCvById(@PathVariable String cvId, @RequestBody Map<String, Object> updates) {

        cvService.updateCv(cvId, updates);
        return ResponseEntity.ok("cv updated");
    }


}
