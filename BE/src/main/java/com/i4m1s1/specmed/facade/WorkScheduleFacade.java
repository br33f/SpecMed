package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.dto.ScheduleDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.persistence.Procedure;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.ProbeRepository;
import com.i4m1s1.specmed.repository.ProcedureRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fasada biznesowa dla operacji związanych z generowaniem grafiku pracy dla Pracownika Medycznego
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Component
public class WorkScheduleFacade {

    private final VisitRepository visitRepository;
    private final ProbeRepository probeRepository;
    private final ProcedureRepository procedureRepository;
    private final MedicalEmployeeRepository medicalEmployeeRepository;

    @Autowired
    public WorkScheduleFacade(VisitRepository visitRepository,
                              ProbeRepository probeRepository,
                              ProcedureRepository procedureRepository,
                              MedicalEmployeeRepository medicalEmployeeRepository) {
        this.visitRepository = visitRepository;
        this.probeRepository = probeRepository;
        this.procedureRepository = procedureRepository;
        this.medicalEmployeeRepository = medicalEmployeeRepository;
    }

    /**
     *
     * @param id
     * @return
     * @throws SMException
     */
    public List<ScheduleDTO> generateScheduleById(String id) throws SMException {
        MedicalEmployee medicalEmployee = medicalEmployeeRepository.findById(id);
        if (medicalEmployee == null) {
            throw new SMException("20171212092645", WarningMsg.DB_NO_RESULTS);
        }
        List<ScheduleDTO> fullSchedule = new ArrayList<>();
        fullSchedule.addAll(getVisitSchedule(medicalEmployee));
        fullSchedule.addAll(getProbeSchedule(medicalEmployee));
        fullSchedule.addAll(getProcedureSchedule(medicalEmployee));
        return fullSchedule;
    }

    /**
     *
     * @param medicalEmployee
     * @return
     * @throws SMException
     */
    public List<ScheduleDTO> generateScheduleByMedicalEmployee(MedicalEmployee medicalEmployee) throws SMException {
        if (medicalEmployee == null) {
            return null;
        }
        return generateScheduleById(medicalEmployee.getId());
    }

    /**
     *
     * @param medicalEmployee
     * @return
     */
    private List<ScheduleDTO> getProcedureSchedule(MedicalEmployee medicalEmployee) {
        List<Procedure> procedures = procedureRepository.findAllByMedicalEmployeeListContains(medicalEmployee);
        List<ScheduleDTO> dtoList = new ArrayList<>();
        for (Procedure p : procedures) {
            dtoList.add(ScheduleDTO.generate(p));
        }
        return dtoList;
    }

    /**
     *
     * @param medicalEmployee
     * @return
     */
    private List<ScheduleDTO> getProbeSchedule(MedicalEmployee medicalEmployee) {
        List<Probe> probes = probeRepository.findAllByMedicalEmployee(medicalEmployee);
        List<ScheduleDTO> dtoList = new ArrayList<>();
        for (Probe p : probes) {
            dtoList.add(ScheduleDTO.generate(p));
        }
        return dtoList;
    }

    /**
     *
     * @param medicalEmployee
     * @return
     */
    private List<ScheduleDTO> getVisitSchedule(MedicalEmployee medicalEmployee) {
        List<Visit> visits = visitRepository.findAllByMedicalEmployee(medicalEmployee);
        List<ScheduleDTO> dtoList = new ArrayList<>();
        for (Visit v : visits) {
            dtoList.add(ScheduleDTO.generate(v));
        }
        return dtoList;
    }
}
