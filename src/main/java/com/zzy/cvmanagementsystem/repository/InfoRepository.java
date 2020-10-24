package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.InfoDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends MongoRepository<InfoDao, String> {
}
