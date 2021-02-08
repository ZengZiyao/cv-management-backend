package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.MembershipDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends MongoRepository<MembershipDao, String> {
}
