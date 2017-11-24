package com.i4m1s1.specmed.service;

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
public class ProviderVisitsByDoctorService {

    @Autowired
    private MedicalEmployeeRepository repository;
    @Autowired
    private OnStartInsertData initMod;

    public List<Visit> provide(String id) {
//        initMod.srajLekarzami();

        MedicalEmployee me = repository.findById(id);
        assert me != null && me.getVisits() != null;
        return me.getVisits();
    }
}
