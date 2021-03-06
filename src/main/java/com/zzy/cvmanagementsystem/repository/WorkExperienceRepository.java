package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.WorkExperienceDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends MongoRepository<WorkExperienceDao, String> {
    List<WorkExperienceDao> findAllByUserId(String userid);

}
