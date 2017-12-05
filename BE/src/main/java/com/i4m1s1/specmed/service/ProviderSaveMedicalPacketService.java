package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.controller.MedicalPacket;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.MedicalPacketRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderSaveMedicalPacketService extends BasicServiceCatch<MedicalPacket, MedicalPacket> {

    @Autowired
    private MedicalPacketRepository repository;

    @Override
    protected BasicResponse<MedicalPacket> provide(BasicRequest<MedicalPacket> request) throws SMException {
        MedicalPacket savedMedicalPacket = repository.save(request.getChunkData());

        BasicResponse<MedicalPacket> response = new BasicResponse<>();
        response.setContent(savedMedicalPacket);
        return response;
    }

}

