package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.PrivateMessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class PrivateMessageOperationsFacadeTest {

    private static final String CUSTOMER_ID = "1";
    private static final String MEDICAL_EMPLOYEE_ID = "2";
    private static final String CONTENT = "test content";
    private Customer customer;
    private MedicalEmployee medicalEmployee;

    @Mock
    private PrivateMessageRepository messageRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private MedicalEmployeeRepository medicalEmployeeRepository;
    @InjectMocks
    private PrivateMessageOperationsFacade facade;

    @Before
    public void init() {
        customer = new Customer();
        medicalEmployee = new MedicalEmployee();
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(customer);
        when(medicalEmployeeRepository.findById(MEDICAL_EMPLOYEE_ID)).thenReturn(medicalEmployee);
    }

    @Test
    public void saveMessageFromCustomerTest() throws Exception {
        //given
        //when
        PrivateMessage result = facade.saveMessageFromCustomer(CUSTOMER_ID, MEDICAL_EMPLOYEE_ID, CONTENT);

        //then
        verify(messageRepository).save(result);
        assertEquals(CONTENT, result.getContent());
        assertTrue(result.isCustomerSender());
        assertEquals(customer, result.getCustomer());
        assertEquals(medicalEmployee, result.getMedicalEmployee());
        assertNotNull(result.getSendTime());
    }

    @Test
    public void saveMessageFromMedicalEmployeeTest() throws Exception {
        //given
        //when
        PrivateMessage result = facade.saveMessageFromMedicalEmployee(CUSTOMER_ID, MEDICAL_EMPLOYEE_ID, CONTENT);

        //then
        verify(messageRepository).save(result);
        assertFalse(result.isCustomerSender());

    }

}