package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.AcademicQualificationDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicQualificationRepository extends MongoRepository<AcademicQualificationDao, String> {
}
