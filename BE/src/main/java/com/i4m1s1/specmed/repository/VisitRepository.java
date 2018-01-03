package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repozytorium zawierające dane wizyt
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public interface VisitRepository extends MongoRepository<Visit, String> {
    /**
     * Metoda zapewniająca wyszukanie wszystkich danych wizyt konkretnego pracownika medycznego
     * @param medicalEmployee dane pracownika medycznego
     * @param pageable lista wizyt
     * @return Lista danych wizyt danego pracownika medycznego
     */
    Page<Visit> findAllByMedicalEmployee(MedicalEmployee medicalEmployee, Pageable pageable);
    Visit findById(String id);
    List<Visit> findAllByMedicalEmployee(MedicalEmployee medicalEmployee);

    List<Visit> findAllByMedicalEmployeeId(String id);
    List<Visit> findAllByCustomerId(String id);
}