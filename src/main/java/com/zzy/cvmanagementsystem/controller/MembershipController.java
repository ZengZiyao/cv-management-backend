package com.zzy.cvmanagementsystem.controller;

import com.zzy.cvmanagementsystem.dto.MembershipDto;
import com.zzy.cvmanagementsystem.service.MembershipService;
import com.zzy.cvmanagementsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cv/memberships")
@CrossOrigin
public class MembershipController {
    private MembershipService membershipService;
    private UserService userService;

    MembershipController(MembershipService membershipService, UserService userService) {
        this.membershipService = membershipService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity getAllMemberships(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        List<MembershipDto> membershipDtos = membershipService.getAllMemberships(userid);
        return ResponseEntity.ok(membershipDtos);
    }

    @PostMapping("")
    public ResponseEntity addMembership(@RequestBody MembershipDto membershipDto, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getUserPrincipal().getName();
        String userid = userService.getUser(username).getId();

        membershipService.AddMembership(membershipDto, userid);
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
