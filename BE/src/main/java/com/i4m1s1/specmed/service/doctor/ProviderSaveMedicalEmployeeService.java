package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveMedicalEmployeeService extends BasicServiceCatch<MedicalEmployee, MedicalEmployee> {

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    protected BasicResponse<MedicalEmployee> provide(BasicRequest<MedicalEmployee> request) throws SMException {
        repository.save(request.getChunkData());
        BasicResponse<MedicalEmployee> response = new BasicResponse<>();
        response.setContent(request.getChunkData());
        return response;
    }
}
