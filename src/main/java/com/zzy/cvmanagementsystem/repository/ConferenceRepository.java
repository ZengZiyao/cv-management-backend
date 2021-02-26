package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.ConferenceDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceRepository extends MongoRepository<ConferenceDao, String> {
    List<ConferenceDao> findByUserId(String userid);
}
