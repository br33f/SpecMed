package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderDoctorBasicDataService  extends ServiceCatchBasic<List<DoctorBasicDataDTO>> {

    @Autowired
    private MedicalEmployeeRepository repository;

    public List<DoctorBasicDataDTO> provide() {

        List<MedicalEmployee> medicalEmployeeList = repository.findAll();
        List<DoctorBasicDataDTO> result = new ArrayList<>();
        for (MedicalEmployee me : medicalEmployeeList) {
            result.add(new DoctorBasicDataDTO(me));
        }
        return result;
    }
}
