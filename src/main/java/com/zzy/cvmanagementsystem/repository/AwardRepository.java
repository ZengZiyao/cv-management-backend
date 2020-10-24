package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.AwardDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends MongoRepository<AwardDao, String> {
}
