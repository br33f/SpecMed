package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.dto.ScheduleDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.ProbeRepository;
import com.i4m1s1.specmed.repository.ProcedureRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class WorkScheduleFacadeTest {

    private static final String MEDICAL_EMPLOYEE_ID = "123";
    private MedicalEmployee medicalEmployee;

    @Mock
    private VisitRepository visitRepository;
    @Mock
    private ProbeRepository probeRepository;
    @Mock
    private ProcedureRepository procedureRepository;
    @Mock
    private MedicalEmployeeRepository medicalEmployeeRepository;
    @InjectMocks
    private WorkScheduleFacade facade;

    @Before
    public void init() {
        medicalEmployee = new MedicalEmployee();
        medicalEmployee.setId(MEDICAL_EMPLOYEE_ID);
        when(medicalEmployeeRepository.findById(MEDICAL_EMPLOYEE_ID)).thenReturn(medicalEmployee);
    }

    @Test
    public void generateScheduleByIdShouldGenerateMinorSchedules() throws Exception {
        //given
        //when
        List<ScheduleDTO> result = facade.generateScheduleById(MEDICAL_EMPLOYEE_ID);

        //then
        verify(probeRepository).findAllByMedicalEmployee(medicalEmployee);
        verify(visitRepository).findAllByMedicalEmployee(medicalEmployee);
        verify(procedureRepository).findAllByMedicalEmployeeListContains(medicalEmployee);
    }

    @Test
    public void generateScheduleByMedicalEmployeeShouldReturnNull() throws Exception {
        //given
        //when
        List<ScheduleDTO> result = facade.generateScheduleByMedicalEmployee(null);

        //then
        assertNull(result);
    }

    @Test
    public void generateScheduleByMedicalEmployeeShouldInvokeFind() throws Exception {
        //given

        //when
        List<ScheduleDTO> result = facade.generateScheduleByMedicalEmployee(medicalEmployee);

        //then
        verify(medicalEmployeeRepository).findById(MEDICAL_EMPLOYEE_ID);
    }


}