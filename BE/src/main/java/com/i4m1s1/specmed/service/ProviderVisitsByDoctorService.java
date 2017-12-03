package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.VisitBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.catchs.*;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

@Service
public class ProviderVisitsByDoctorService extends ListServiceCatch<Visit, VisitBasicDataDTO> {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;

    @Override
    protected ListResponse<VisitBasicDataDTO> provide(ListRequest<Visit> request) throws SMException {
        Visit visitReq = request.getSearchCriteria();
        MedicalEmployee medicalEmployeeResult = medicalEmployeeRepository.findById(visitReq.getId());
        Page<Visit> page = visitRepository.findAllByMedicalEmpoyee(medicalEmployeeResult, request.getPageCriteria().getAsPageRequest());

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
