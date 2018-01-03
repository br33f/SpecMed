package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderGetDoctorSpecializationService extends BasicServiceCatch<String, List<String>> {

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    protected BasicResponse<List<String>> provide(BasicRequest<String> request) throws SMException {
        MedicalEmployee employee = repository.findById(request.getChunkData());

        BasicResponse<List<String>> response = new BasicResponse<>();
        response.setContent(employee.getSpecializationList());

        return response;
    }
}
