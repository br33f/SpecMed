package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.facade.MailSendingFacade;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.i4m1s1.specmed.core.helper.CommonHelper.checkNotNull;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveCustomerService extends BasicServiceCatch<Customer, Customer>{

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private MailSendingFacade facade;

    @Override
    protected BasicResponse<Customer> provide(BasicRequest<Customer> request) throws SMException {
        Customer customer = repository.save(request.getChunkData());
        BasicResponse<Customer> response = new BasicResponse<>();
        response.setContent(customer);
        if(!request.getChunkData().getContactData().getEmail().contains("test")) {
            facade.sendRegisterEmailForCustomer(customer);
        }
        return response;
    }

    @Override
    protected void validate() {
        isValid = isValid && checkNotNull(request.getChunkData());
        isValid = isValid && checkNotNull(request.getChunkData().getContactData());
        isValid = isValid && checkNotNull(request.getChunkData().getContactData().getEmail());
    }
}
