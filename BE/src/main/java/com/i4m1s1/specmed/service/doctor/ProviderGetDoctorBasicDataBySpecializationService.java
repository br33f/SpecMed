package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderGetDoctorBasicDataBySpecializationService extends BasicServiceCatch<String, List<DoctorBasicDataDTO>> {

    @Autowired
    private MedicalEmployeeRepository repository;

    /**
     * Metoda usługowa pobierająca podstawowe informacje o lekarzach o danej specjalizacji
     * @param request żądanie o wszystkie dane lekarzy o danej specjalizacji {@link MedicalEmployeeRepository}
     * @return Lista danych wszystkich lekarzy o danej specjlizacji
     * @throws SMException
     */
    @Override
    protected BasicResponse<List<DoctorBasicDataDTO>> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<List<DoctorBasicDataDTO>> basicDataDTOBasicResponse =new BasicResponse<>();

        List<String> specializationList = new ArrayList<>();
        specializationList.add(request.getChunkData());

        MedicalEmployee medicalEmployeeExample = new MedicalEmployee();
        medicalEmployeeExample.setSpecializationList(specializationList);

        List<MedicalEmployee> page = repository.findAll(Example.of(medicalEmployeeExample));

        List<DoctorBasicDataDTO> result = new ArrayList<>();
        for (MedicalEmployee me : page) {
            result.add(new DoctorBasicDataDTO(me));
        }
        basicDataDTOBasicResponse.setContent(result);
        return  basicDataDTOBasicResponse;
    }
}
