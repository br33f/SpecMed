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
 * Created by br33 on 25.11.2017.
 */
@Service
public class ProviderGetEmployeeService extends BasicServiceCatch<String, Employee> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    protected BasicResponse<Employee> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<Employee> response = new BasicResponse<>();
        response.setContent(repository.findOne(request.getChunkData()));

        return response;
    }

}