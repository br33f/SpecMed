package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.persistence.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MailSendingFacadeTest {

    private static final String EMAIL = "t885476@mvrht.net";
    private Customer customer;

    @Mock
    private MimeMessage message;
    @Mock
    private JavaMailSender emailSender;
    @InjectMocks
    private MailSendingFacade facade;

    @Before
    public void init() {
        when(emailSender.createMimeMessage()).thenReturn(message);
        customer = new Customer();
        prepareCustomer();
    }

    @Test
    public void sendRegisterEmailForCustomer() throws Exception {
        //given
        //when
        facade.sendRegisterEmailForCustomer(customer);

        //then
        verify(emailSender).send(message);
        verify(message).setHeader("Content-type", "text/html; charset=UTF-16");
    }

    private void prepareCustomer() {
        ContactData contactData = new ContactData();
        contactData.setEmail(EMAIL);
        customer.setContactData(contactData);
    }
}