package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serwis dostarczajacy aktualne dane lekarzy
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Service
public class ProviderDoctorBasicDataService extends ListServiceCatch<MedicalEmployee, DoctorBasicDataDTO> {

    @Autowired
    private MedicalEmployeeRepository repository;


    /**
     * Metoda usługowa pobierająca informacje o lekarzu ze względu na kryteria
     * @param request żądanie o dane lekarzy {@link MedicalEmployeeRepository}
     * @return Lista danych lekarzy
     * @throws SMException
     */

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
