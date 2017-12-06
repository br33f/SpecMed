package com.i4m1s1.specmed.service.insurance;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Insurance;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.InsuranceRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSaveInsuranceService extends BasicServiceCatch<Insurance, Insurance> {

    @Autowired
    private InsuranceRepository repository;

    @Override
    protected BasicResponse<Insurance> provide(BasicRequest<Insurance> request) throws SMException {
        BasicResponse<Insurance> response = new BasicResponse<>();
        response.setContent(repository.save(request.getChunkData()));

        return response;
    }
}
