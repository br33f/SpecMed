package com.i4m1s1.specmed.facade;

import com.google.common.collect.Lists;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.Permission;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Component
public class BindingUserAccOperationFacade {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Customer bindAccountIfExists(String userEmail,
                                        String userPasswd,
                                        String providedPesel,
                                        String providedCustomerId) throws SMException {
        User user = prepareUserToBind(userEmail, userPasswd);
        Customer customer = prepareCustomerToBind(providedCustomerId, providedPesel);
        if (user != null && customer != null) {
            bindAcc(user, customer);
            return customer;
        }
        return null;
    }

    private void bindAcc(User user, Customer customer) {
        ArrayList<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.CUSTOMER);
        user.setPermissions(permissions);
        user.setEntityId(customer.getId());
        userRepository.save(user);
    }

    private Customer prepareCustomerToBind(String customerId, String providedPesel) throws SMException {
        Customer customer = customerRepository.findById(customerId);
        if (customer == null || customer.getPersonalData() == null) {
            throw new SMException("20171223031555", WarningMsg.DB_NO_RESULTS);
        }
        String customerPesel = customer.getPersonalData().getPesel();
        if (providedPesel.equals(customerPesel)) {
            return customer;
        } else {
            throw new SMException("20171223032129", WarningMsg.CANT_BIND_WRONG_DATA); //to nie koniecznie musi byc exc.
        }
    }

    private User prepareUserToBind(String userEmail, String userPasswd) throws SMException {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new SMException("20171223031825", WarningMsg.DB_NO_RESULTS);
        }
        boolean isSamePasswd = BCrypt.checkpw(userPasswd, user.getPassword());
        if (isSamePasswd) {
            return user;
        } else {
            throw new SMException("20171223032537", WarningMsg.CANT_BIND_WRONG_PASSWORD);
        }
    }
}
