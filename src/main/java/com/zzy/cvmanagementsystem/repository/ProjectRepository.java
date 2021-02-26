package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.ProjectDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectDao, String> {
    List<ProjectDao> findAllByUserId(String userid);

}
