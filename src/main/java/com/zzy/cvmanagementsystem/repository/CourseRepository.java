package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.CourseDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<CourseDao, String> {
    List<CourseDao> findAllByUserId(String userid);

}
