package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.dto.VisitBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderGetFreeVisitService extends BasicServiceCatch<String, List<VisitBasicDataDTO>> {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;

    @Override
    protected BasicResponse<List<VisitBasicDataDTO>> provide(BasicRequest<String> request) throws SMException {
        MedicalEmployee medicalEmployee = medicalEmployeeRepository.findById(request.getChunkData());
        if (medicalEmployee == null) {
            throw new SMException("20171213030505", WarningMsg.DB_NO_RESULTS);
        }
        List<Visit> visits = visitRepository.findAllByMedicalEmployee(medicalEmployee);
        List<VisitBasicDataDTO> dtos = new ArrayList<>();
        for (Visit v : visits) {
            if (v.getCustomer() == null) {
                dtos.add(new VisitBasicDataDTO(v));
            }
        }
        BasicResponse<List<VisitBasicDataDTO>> response = new BasicResponse<>();
        response.setContent(dtos);
        return response;
    }
}
