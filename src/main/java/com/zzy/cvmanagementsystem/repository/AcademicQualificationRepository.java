package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.AcademicQualificationDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicQualificationRepository extends MongoRepository<AcademicQualificationDao, String> {
    List<AcademicQualificationDao> findAllByUserId(String userid);
}
