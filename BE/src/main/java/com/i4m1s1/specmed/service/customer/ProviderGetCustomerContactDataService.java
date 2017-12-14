package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderGetCustomerContactDataService extends BasicServiceCatch<String, ContactData> {

    @Autowired
    private CustomerRepository repository;

    @Override
    protected BasicResponse<ContactData> provide(BasicRequest<String> request) throws SMException {
        Customer customer = repository.findById(request.getChunkData());

        BasicResponse<ContactData> response = new BasicResponse<>();
        response.setContent(customer.getContactData());

        return response;
    }
}
