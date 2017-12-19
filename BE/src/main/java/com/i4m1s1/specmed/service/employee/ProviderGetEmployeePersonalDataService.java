package com.i4m1s1.specmed.service.employee;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderGetEmployeePersonalDataService extends BasicServiceCatch<String, PersonalData> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    protected BasicResponse<PersonalData> provide(BasicRequest<String> request) throws SMException {
        Employee employee = repository.findById(request.getChunkData());

        BasicResponse<PersonalData> response = new BasicResponse<>();
        response.setContent(employee.getPersonalData());

        return response;
    }
}
