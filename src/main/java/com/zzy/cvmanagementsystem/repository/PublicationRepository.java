package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.PublicationDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends MongoRepository<PublicationDao, String> {
    List<PublicationDao> findAllByUserId(String userid);

}
