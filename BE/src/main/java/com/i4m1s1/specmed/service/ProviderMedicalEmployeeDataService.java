package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProviderMedicalEmployeeDataService extends ListServiceCatch<MedicalEmployee, MedicalEmployee>{

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    protected ListResponse<MedicalEmployee> provide(ListRequest<MedicalEmployee> request) throws SMException {
        MedicalEmployee employee = request.getSearchCriteria();
        Example<MedicalEmployee> exampleEmployee = Example.of(employee);
        Page<MedicalEmployee> page = repository.findAll(exampleEmployee, request.getPageCriteria().getAsPageRequest());
        return new ListResponse<>(page);
    }
}
