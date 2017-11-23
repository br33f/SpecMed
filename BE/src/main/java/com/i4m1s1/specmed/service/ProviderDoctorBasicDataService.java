package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.core.enums.Gender;
import com.i4m1s1.specmed.core.enums.Specialization;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderDoctorBasicDataService {

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

//    @PostConstruct
    private void prepareShit() {
        //INIT
        MedicalEmployee me = new MedicalEmployee();
        PersonalData pe = new PersonalData();
        pe.setSurname("sm");
        pe.setBirthday(new Date());
        pe.setPesel("99999912345");
        pe.setName("name");
        pe.setGender(Gender.MALE);
        List<Specialization> spec = new ArrayList<>();
        spec.add(Specialization.PEDIATRA);

        me.setPersonalData(pe);
        me.setSpecializationList(spec);
        repository.save(me);
    }
}
