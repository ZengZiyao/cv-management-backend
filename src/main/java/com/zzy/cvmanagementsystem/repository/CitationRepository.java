package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.CitationDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitationRepository extends MongoRepository<CitationDao, String> {
    List<CitationDao> findAllByUserId(String userid);
}
