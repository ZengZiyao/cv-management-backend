package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.MembershipDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends MongoRepository<MembershipDao, String> {
    List<MembershipDao> findAllByUserId(String userid);

}
