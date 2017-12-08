package com.i4m1s1.specmed.service.employee;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serwis udostępniający dodanie nowych pracowników
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSaveEmployeeService extends BasicServiceCatch<Employee, Employee> {

    @Autowired
    private EmployeeRepository repository;

    /**
     * Metoda usługowa dodająca nowego pracownika
     * @param request żądanie zawierające dane pracownika {@link EmployeeRepository}
     * @return Lista dodanego pracownika
     * @throws SMException
     */
    @Override
    protected BasicResponse<Employee> provide(BasicRequest<Employee> request) throws SMException {
        Employee savedEmployee = repository.save(request.getChunkData());

        BasicResponse<Employee> response = new BasicResponse<>();
        response.setContent(savedEmployee);
        return response;
    }

}
