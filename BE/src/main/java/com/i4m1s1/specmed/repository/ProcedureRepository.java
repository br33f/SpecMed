package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Procedure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface ProcedureRepository extends MongoRepository<Procedure, String> {
    List<Procedure> findAllByMedicalEmployeeListContains(MedicalEmployee medicalEmployee);
}
