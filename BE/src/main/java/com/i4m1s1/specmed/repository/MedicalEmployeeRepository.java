package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.core.enums.Specialization;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface MedicalEmployeeRepository extends MongoRepository<MedicalEmployee, String> {
    public List<MedicalEmployee> findBySpecializationListContains(Specialization spec);
}
