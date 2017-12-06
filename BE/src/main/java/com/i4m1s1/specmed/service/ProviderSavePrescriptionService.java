package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Prescription;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.PrescriptionRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serwis udostępniający zapis recepty w systemie
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSavePrescriptionService extends BasicServiceCatch<Prescription, Prescription> {

    @Autowired
    private PrescriptionRepository repository;

    /**
     * Metoda usługowa do wystawiania recept
     * @param request żądanie zawierające dane recept {@link PrescriptionRepository}
     * @return Lista danych o recepcie
     * @throws SMException
     */
    @Override
    protected BasicResponse<Prescription> provide(BasicRequest<Prescription> request) throws SMException {
        Prescription savedPrescription = repository.save(request.getChunkData());

        BasicResponse<Prescription> response = new BasicResponse<>();
        response.setContent(savedPrescription);
        return response;
    }

}
