package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.visit.ProviderVisitsByDoctorService;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ProviderVisitsByDoctorServiceTest {

    private static final String EXPECTED_MESSAGE = "20171124044256" + ":" + WarningMsg.DB_NO_RESULTS.message();
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Mock
    private VisitRepository repository;
    @InjectMocks
    private ProviderVisitsByDoctorService service;

    @Test
    public void provideShouldThrowExceptionWhenNull() throws Exception {
        //given
        ListRequest request = new ListRequest();
        String id = "sampleid";
        when(repository.findAll()).thenReturn(null);
        exception.expect(SMException.class);
        exception.expectMessage(EXPECTED_MESSAGE);

        //when
        service.provide(request);

        //then
    }
}