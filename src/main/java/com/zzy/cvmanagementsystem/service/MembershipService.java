package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dto.MembershipDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembershipService {
    List<MembershipDto> getAllMemberships(String userid);

    void AddMembership(MembershipDto membershipDto, String userid);

    void updateMembership(String id, MembershipDto membershipDto);

    void deleteMembership(String id);

    void deleteByUserId(String userid);

}
