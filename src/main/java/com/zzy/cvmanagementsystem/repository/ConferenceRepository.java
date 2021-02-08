package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.ConferenceDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends MongoRepository<ConferenceDao, String> {
}
