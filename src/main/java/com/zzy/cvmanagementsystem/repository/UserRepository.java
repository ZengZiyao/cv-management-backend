package com.zzy.cvmanagementsystem.repository;

import com.zzy.cvmanagementsystem.dao.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDao, String> {
    UserDao findByUsername(String username);
}
