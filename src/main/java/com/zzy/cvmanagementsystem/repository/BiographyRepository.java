package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.BiographyDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiographyRepository extends MongoRepository<BiographyDao, String> {
}
