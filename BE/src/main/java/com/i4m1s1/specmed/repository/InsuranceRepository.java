package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Insurance;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repozytorium zawierające dane o ubezpieczeniach
 */
public interface InsuranceRepository extends MongoRepository<Insurance, String> {

}
