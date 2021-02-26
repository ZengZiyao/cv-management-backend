package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.AwardDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends MongoRepository<AwardDao, String> {
    List<AwardDao> findAllByUserId(String userid);

}
