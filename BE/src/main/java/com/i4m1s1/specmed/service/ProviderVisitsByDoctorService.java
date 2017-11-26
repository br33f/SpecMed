package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.response.ProviderVisitsByDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderVisitsByDoctorService extends ServiceCatch<ProviderVisitsByDoctorResponse, String> {

    @Autowired
    private MedicalEmployeeRepository repository;

    @Override
    public ProviderVisitsByDoctorResponse provide(String doctorId) throws SMException {

        MedicalEmployee me = repository.findById(doctorId);
        if (me == null) {
            throw new SMException("20171124044256", WarningMsg.DB_NO_RESULTS);
        }
        return new ProviderVisitsByDoctorResponse(me.getVisits());
    }
}
