package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Patient;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.PatientRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderSavePatientService extends BasicServiceCatch<Patient, Patient> {

    @Autowired
    private PatientRepository repository;

    @Override
    protected BasicResponse<Patient> provide(BasicRequest<Patient> request) throws SMException {
        Patient savedPatient = repository.save(request.getChunkData());

        BasicResponse<Patient> response = new BasicResponse<>();
        response.setContent(savedPatient);
        return response;
    }

}
