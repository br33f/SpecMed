package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderDoctorBasicDataService extends ListServiceCatch<MedicalEmployee, DoctorBasicDataDTO> {

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    protected ListResponse<DoctorBasicDataDTO> provide(ListRequest<MedicalEmployee> request) throws SMException {

        MedicalEmployee medicalRequest = request.getSearchCriteria();
        Example<MedicalEmployee> medicalEmployeeExample = Example.of(medicalRequest);

        Page<MedicalEmployee> page = repository.findAll(medicalEmployeeExample, request.getPageCriteria().getAsPageRequest());

        List<DoctorBasicDataDTO> result = new ArrayList<>();
        for (MedicalEmployee me : page.getContent()) {
            result.add(new DoctorBasicDataDTO(me));
        }
        return new ListResponse<>(page.getTotalElements(), page.getTotalPages(), result);
    }
}
