package com.zzy.cvmanagementsystem.service.impl;

import com.zzy.cvmanagementsystem.dao.MembershipDao;
import com.zzy.cvmanagementsystem.dto.MembershipDto;
import com.zzy.cvmanagementsystem.exception.NotFoundException;
import com.zzy.cvmanagementsystem.repository.MembershipRepository;
import com.zzy.cvmanagementsystem.service.MembershipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {
    private MembershipRepository membershipRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<MembershipDao> getAllMemberships() {
        return membershipRepository.findAll();
    }

    @Override
    public void AddMembership(MembershipDto membershipDto) {
        MembershipDao membershipDao = new MembershipDao();
        membershipDao.setCountry(membershipDto.getCountry());
        membershipDao.setDesignation(membershipDto.getDesignation());
        membershipDao.setEndTime(membershipDto.getEndTime());
        membershipDao.setInstitution(membershipDto.getInstitution());
        membershipDao.setStartTime(membershipDto.getStartTime());
        membershipDao.setState(membershipDto.getState());
        membershipRepository.save(membershipDao);
    }

    @Override
    public void updateMembership(String id, MembershipDto membershipDto) {
        MembershipDao membershipDao = membershipRepository.findById(id).orElseThrow(() -> new NotFoundException("Membership not found"));
        membershipDao.setCountry(membershipDto.getCountry());
        membershipDao.setDesignation(membershipDto.getDesignation());
        membershipDao.setEndTime(membershipDto.getEndTime());
        membershipDao.setInstitution(membershipDto.getInstitution());
        membershipDao.setStartTime(membershipDto.getStartTime());
        membershipDao.setState(membershipDto.getState());
        membershipRepository.save(membershipDao);
    }

    @Override
    public void deleteMembership(String id) {
        membershipRepository.deleteById(id);
    }
}
