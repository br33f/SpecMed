package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveCustomerService extends BasicServiceCatch<Customer, Customer>{

    @Autowired
    private CustomerRepository repository;

    @Override
    protected BasicResponse<Customer> provide(BasicRequest<Customer> request) throws SMException {
        repository.save(request.getChunkData());
        BasicResponse<Customer> response = new BasicResponse<>();
        response.setContent(request.getChunkData());
        return response;
    }
}
