package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serwis udostępniający aktualne wszystkie dane lekarzy
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Service
public class ProviderGetAllDoctorBasicDataService extends BasicServiceCatch<Object, List<DoctorBasicDataDTO>> {

    @Autowired
    private MedicalEmployeeRepository repository;

    /**
     * Metoda usługowa pobierająca wszystkie informacje lekarzy
     * @param request żądanie o wszystkie dane lekarzy {@link MedicalEmployeeRepository}
     * @return Lista danych wszystkich lekarzy
     * @throws SMException
     */
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
