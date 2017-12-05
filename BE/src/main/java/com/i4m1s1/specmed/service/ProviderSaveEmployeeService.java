package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSaveEmployeeService extends BasicServiceCatch<Employee, Employee> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    protected BasicResponse<Employee> provide(BasicRequest<Employee> request) throws SMException {
        Employee savedEmployee = repository.save(request.getChunkData());

        BasicResponse<Employee> response = new BasicResponse<>();
        response.setContent(savedEmployee);
        return response;
    }

}