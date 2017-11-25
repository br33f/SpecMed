package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.enums.WarningMsg;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class ProviderVisitsByDoctorServiceTest {

    private static final String EXPECTED_MESSAGE = "20171124044256" + ":" + WarningMsg.DB_NO_RESULTS.message();
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Mock
    private MedicalEmployeeRepository repository;
    @InjectMocks
    private ProviderVisitsByDoctorService service;

    @Test
    public void provideShouldThrowExceptionWhenNull() throws Exception {
        //given
        String id = "sampleid";
        MedicalEmployee employee = null;
        when(repository.findById(id)).thenReturn(employee);
        exception.expect(SMException.class);
        exception.expectMessage(EXPECTED_MESSAGE);

        //when
        service.provide(id);

        //then
    }
}