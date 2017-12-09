package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.Unit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface UnitRepository extends MongoRepository<Unit, String>{
}
