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
@CrossOrigin
public class CvController {

    private final CvService cvService;

    public CvController(CvServiceImpl cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/{cvId}")
    public ResponseEntity<CvResponse> getCvById(@PathVariable String cvId) {
        CvResponse cvResponse = cvService.getCvById(cvId);
        return ResponseEntity.ok(cvResponse);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> postCv(@RequestBody CvDto cvDto) {
        String cvId = cvService.postCv(cvDto);

        Map<String, String> map = new HashMap<>();
        map.put("cvId", cvId);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cvId}")
    public ResponseEntity<Map> deleteCvById(@PathVariable String cvId) {
        cvService.deleteCv(cvId);
        Map<String, String> map = new HashMap<>();
        map.put("message", "cv deleted");

        return ResponseEntity.ok(map);
    }

    @PatchMapping("/{cvId}")
    public ResponseEntity<Map> patchCvById(@PathVariable String cvId, @RequestBody Map<String, Object> updates) {

        cvService.updateCv(cvId, updates);
        Map<String, String> map = new HashMap<>();
        map.put("message", "cv updated");
        return ResponseEntity.ok(map);
    }


}
