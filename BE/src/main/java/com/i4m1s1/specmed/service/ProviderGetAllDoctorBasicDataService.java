package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
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
public class ProviderGetAllDoctorBasicDataService extends BasicServiceCatch<Object, List<DoctorBasicDataDTO>> {

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    protected BasicResponse<List<DoctorBasicDataDTO>> provide(BasicRequest<Object> request) throws SMException {
        BasicResponse<List<DoctorBasicDataDTO>> basicDataDTOBasicResponse =new BasicResponse<>();

        List<MedicalEmployee> page = repository.findAll();

        List<DoctorBasicDataDTO> result = new ArrayList<>();
        for (MedicalEmployee me : page) {
            result.add(new DoctorBasicDataDTO(me));
        }
        basicDataDTOBasicResponse.setContent(result);
        return  basicDataDTOBasicResponse;
    }
}
