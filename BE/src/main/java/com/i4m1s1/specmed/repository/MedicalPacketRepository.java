package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.controller.MedicalPacket;
import com.i4m1s1.specmed.persistence.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MedicalPacketRepository extends MongoRepository<MedicalPacket, String> {

}
