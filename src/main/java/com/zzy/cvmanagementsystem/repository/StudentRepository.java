package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.StudentDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentDao, String> {
}
