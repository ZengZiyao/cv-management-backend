package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.StatusDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends MongoRepository<StatusDao, String> {
    StatusDao findByUserId(String userid);
}
