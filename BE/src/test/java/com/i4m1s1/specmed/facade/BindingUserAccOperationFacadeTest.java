package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.dict.Permission;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class BindingUserAccOperationFacadeTest {

    private static final String EXPECTED_MESSAGE_NO_CUSTOMER = "20171223031555" + ":" + WarningMsg.DB_NO_RESULTS.message();
    private static final String EXPECTED_MESSAGE_NO_USER = "20171223031825" + ":" + WarningMsg.DB_NO_RESULTS.message();
    private static final String EXPECTED_MESSAGE_WRONG_DATA = "20171223032129" + ":" + WarningMsg.CANT_BIND_WRONG_DATA.message();
    private static final String EXPECTED_MESSAGE_WRONG_PASSWD = "20171223032537" + ":" + WarningMsg.CANT_BIND_WRONG_PASSWORD.message();

    private static final String WRONG_CUSTOMER_ID = "WRONG_CUSTOMER_ID";
    private static final String CUSTOMER_ID = "123";
    private static final String WRONG_EMAIL = "WRONG_EMAIL";
    private static final String EMAIL = "t885476@mvrht.net";
    private static final String PESEL = "11111111911";
    private static final String PASSWD = "passwd";
    private static final String HASHED_PASSWD = hashPasswd(PASSWD);
    private static final String WRONG_PASSWD = "WRONG_PASSWORD";
    private static final String WRONG_PESEL = "WRONG_PESEL";
    private Customer customer;
    private User user;

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private BindingUserAccOperationFacade facade;

    @Before
    public void init() {
        prepareUser();
        prepareCustomer();
        when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(customer);
    }

    @Test
    public void bindAccountIfExists() throws Exception {
        //given

        //when
        Customer result = facade.bindAccountIfExists(EMAIL, PASSWD, PESEL, CUSTOMER_ID);

        //then
        verify(userRepository).save(user);
        assertEquals(CUSTOMER_ID, user.getEntityId());
        assertEquals(customer, result);
        assertTrue(user.getPermissions().contains(Permission.CUSTOMER));
        assertEquals(1, user.getPermissions().size());
    }

    @Test
    public void bindAccountIfExistsNoCustomerFound() throws Exception {
        //given
        exception.expectMessage(EXPECTED_MESSAGE_NO_CUSTOMER);

        //when
        facade.bindAccountIfExists(EMAIL, PASSWD, PESEL, WRONG_CUSTOMER_ID);
        //then
    }

    @Test
    public void bindAccountIfExistsNoCustomerFoundWithPersonalData() throws Exception {
        //given
        customer.setPersonalData(null);
        exception.expectMessage(EXPECTED_MESSAGE_NO_CUSTOMER);

        //when
        facade.bindAccountIfExists(EMAIL, PASSWD, PESEL, CUSTOMER_ID);
        //then
    }

    @Test
    public void bindAccountIfExistsNoUserFound() throws Exception {
        //given
        exception.expectMessage(EXPECTED_MESSAGE_NO_USER);

        //when
        facade.bindAccountIfExists(WRONG_EMAIL, PASSWD, PESEL, CUSTOMER_ID);
        //then
    }

    @Test
    public void bindAccountIfExistsWrongPassword() throws Exception {
        //given
        exception.expectMessage(EXPECTED_MESSAGE_WRONG_PASSWD);

        //when
        facade.bindAccountIfExists(EMAIL, WRONG_PASSWD, PESEL, CUSTOMER_ID);
        //then
    }

    @Test
    public void bindAccountIfExistsWrongWrongPesel() throws Exception {
        //given
        exception.expectMessage(EXPECTED_MESSAGE_WRONG_DATA);

        //when
        facade.bindAccountIfExists(EMAIL, PASSWD, WRONG_PESEL, CUSTOMER_ID);
        //then
    }

    private void prepareCustomer() {
        PersonalData personalData = new PersonalData();
        personalData.setPesel(PESEL);
        customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setPersonalData(personalData);
    }

    private void prepareUser() {
        user = new User();
        user.setEmail(EMAIL);
        user.setPassword(HASHED_PASSWD);
    }

    private static String hashPasswd(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}