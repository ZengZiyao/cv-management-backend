package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.PublicationDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends MongoRepository<PublicationDao, String> {
}
