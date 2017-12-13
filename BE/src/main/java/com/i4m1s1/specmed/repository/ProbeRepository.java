package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Probe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface ProbeRepository extends MongoRepository<Probe, String>{
    List<Probe> findAllByMedicalEmployee(MedicalEmployee medicalEmployee);
}
