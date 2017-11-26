package com.i4m1s1.specmed.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.annotation.ParamMap;
import com.i4m1s1.specmed.dto.VisitBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.common.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

@Service
@ParamMap(name = {"id", "inne parametry z requesta..."})
public class ProviderVisitsByDoctorService extends ServiceCatch<ListResponse<VisitBasicDataDTO>, ListRequest> {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;

    @Override
    public ListResponse<VisitBasicDataDTO> provide(ListRequest request) throws SMException {

        String id = params.get("id").asText();
        MedicalEmployee medicalEmployeeResult = medicalEmployeeRepository.findById(id);
        Page<Visit> page = visitRepository.findAllByMedicalEmpoyee(medicalEmployeeResult, request.getPageCriteria().getAsPageRequest());

//        @SuppressWarnings({"unchecked","rawtypes"}) jezeli wyzej nie da sie Page<Visit>
        List<Visit> visitList = page.getContent();
        List<VisitBasicDataDTO> resultList = new ArrayList<>();
        for (Visit visit : visitList) {
            resultList.add(new VisitBasicDataDTO(visit));
        }
        long totalCount = page.getTotalElements();
        long totalPages = page.getTotalPages();
        return new ListResponse<>(totalCount, totalPages, resultList);
    }
}
