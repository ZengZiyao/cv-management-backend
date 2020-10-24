package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.JournalDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends MongoRepository<JournalDao, String> {
}
