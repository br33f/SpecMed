package com.i4m1s1.specmed.service.visit;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.VisitDTO;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serwis udostępniający dodawanie nowej wizyty
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSaveVisitService extends BasicServiceCatch<VisitDTO, VisitDTO> {

    @Autowired
    private VisitRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;

    /**
     * Metoda usługowa pobierająca dane wizyt
     * @param request żądanie zawierające dane o wizycie {@link VisitRepository}
     * @return Lista danych wizyt
     * @throws SMException
     */
    @Override
    protected BasicResponse<VisitDTO> provide(BasicRequest<VisitDTO> request) throws SMException {
        VisitDTO requestVisitDto = request.getChunkData();
        Visit visitToSave = new Visit();

        visitToSave.setPlace(requestVisitDto.getPlace());
        visitToSave.setDateStart(requestVisitDto.getDateStart());
        visitToSave.setDateEnd(requestVisitDto.getDateEnd());
        visitToSave.setPrice(requestVisitDto.getPrice());
        visitToSave.setStatus(requestVisitDto.getStatus());

        MedicalEmployee medicalEmployee = medicalEmployeeRepository.findById(requestVisitDto.getMedicalEmployeeId());
        visitToSave.setMedicalEmployee(medicalEmployee);

        if (requestVisitDto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(requestVisitDto.getCustomerId());
            visitToSave.setCustomer(customer);
        }

        Visit savedVisit = repository.save(visitToSave);

        VisitDTO visitDTO = new VisitDTO(savedVisit);
        BasicResponse<VisitDTO> response = new BasicResponse<>();
        response.setContent(visitDTO);
        return response;
    }

}
