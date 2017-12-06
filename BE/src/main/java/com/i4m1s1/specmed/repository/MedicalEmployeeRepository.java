package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.MedicalEmployee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repozytorium zawierające dane pracowników medycznych
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public interface MedicalEmployeeRepository extends MongoRepository<MedicalEmployee, String> {
    /**
     * Metoda zapewniająca wyszukanie danych listy pracowników medycznych o wskazanej specjalności
     * @param spec specjalność pracownika medycznego
     * @return lista pracowników medycznych
     */
    List<MedicalEmployee> findBySpecializationListContains(String spec);

    /**
     * metoda zapewniająca wyszukanie danych pracownika o konkretnym id
     * @param id id pracownika medycznego
     * @return dane pracownika o danym id
     */
    MedicalEmployee findById(String id);

}
