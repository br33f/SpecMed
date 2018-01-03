package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.persistence.PrivateMessage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface PrivateMessageRepository extends MongoRepository<PrivateMessage, String> {
    List<PrivateMessage> findAllByCustomer_Id(ObjectId id);
    List<PrivateMessage> findAllByCustomer_IdAndMedicalEmployee_Id(ObjectId customerId, ObjectId medicalEmployeeId);
}
