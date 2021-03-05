package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.ConnectionDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends MongoRepository<ConnectionDao, String> {
}
