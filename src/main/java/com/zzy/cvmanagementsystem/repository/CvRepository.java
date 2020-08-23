package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.CvDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends MongoRepository<CvDao, String> {
}
