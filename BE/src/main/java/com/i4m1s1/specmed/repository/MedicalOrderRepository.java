package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.MedicalOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repozytorium zawierające dane usług
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface MedicalOrderRepository extends MongoRepository<MedicalOrder, String> {
}
