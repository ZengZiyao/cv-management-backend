package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends MongoRepository<JournalDao, String> {
    List<JournalDao> findByUserId(String userid);
}
