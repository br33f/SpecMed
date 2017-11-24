package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

public interface VisitRepository extends MongoRepository<Visit, String> {
    public List<Visit> findByDay(String day);

}
