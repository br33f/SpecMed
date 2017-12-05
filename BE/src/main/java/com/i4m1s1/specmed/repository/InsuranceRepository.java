package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Insurance;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InsuranceRepository extends MongoRepository<Insurance, String> {

}
