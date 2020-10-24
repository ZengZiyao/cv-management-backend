package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.WorkExperienceDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepository extends MongoRepository<WorkExperienceDao, String> {
}
