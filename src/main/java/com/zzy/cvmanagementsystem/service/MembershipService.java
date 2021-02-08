package com.zzy.cvmanagementsystem.service;

import com.zzy.cvmanagementsystem.dao.MembershipDao;
import com.zzy.cvmanagementsystem.dto.MembershipDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembershipService {
    List<MembershipDao> getAllMemberships();

    void AddMembership(MembershipDto membershipDto);

    void updateMembership(String id, MembershipDto membershipDto);

    void deleteMembership(String id);

}
