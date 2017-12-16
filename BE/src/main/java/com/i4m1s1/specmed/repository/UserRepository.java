package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}