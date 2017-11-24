package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.enums.WarningMsg;
import com.i4m1s1.specmed.initmodules.OnStartInsertData;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderVisitsByDoctorService extends ServiceCatch<List<Visit>, String> {

    @Autowired
    private MedicalEmployeeRepository repository;
    @Autowired
    private OnStartInsertData initMod;

    @Override
    public List<Visit> provide(String id) throws SMException {
//        initMod.srajLekarzami();

        MedicalEmployee me = repository.findById(id);
        if (me == null || me.getVisits() == null) {
            throw new SMException("20171124044256", WarningMsg.DB_NO_RESULTS);
        }
        return me.getVisits();
    }
}
