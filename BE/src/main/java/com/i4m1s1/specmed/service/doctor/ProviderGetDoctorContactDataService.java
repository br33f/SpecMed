package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderGetDoctorContactDataService extends BasicServiceCatch<String, ContactData> {

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    protected BasicResponse<ContactData> provide(BasicRequest<String> request) throws SMException {
        MedicalEmployee employee = repository.findById(request.getChunkData());

        BasicResponse<ContactData> response = new BasicResponse<>();
        response.setContent(employee.getContactData());

        return response;
    }
}
