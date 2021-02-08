package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dao.MembershipDao;
import com.zzy.cvmanagementsystem.dto.MembershipDto;
import com.zzy.cvmanagementsystem.service.MembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/memberships")
@CrossOrigin
public class MembershipController {
    private MembershipService membershipService;

    MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("")
    public ResponseEntity getAllMemberships() {
        List<MembershipDao> membershipDaos = membershipService.getAllMemberships();
        return ResponseEntity.ok(membershipDaos);
    }

    @PostMapping("")
    public ResponseEntity addMembership(@RequestBody MembershipDto membershipDto) {
        membershipService.AddMembership(membershipDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMembership(@PathVariable String id, @RequestBody MembershipDto membershipDto) {
        membershipService.updateMembership(id, membershipDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMembership(@PathVariable String id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }

}
