package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProviderGetCustomerService extends BasicServiceCatch<String, Customer> {

    @Autowired
    private CustomerRepository repository;

    @Override
    protected BasicResponse<Customer> provide(BasicRequest<String> request) throws SMException {
        Customer customer = repository.findById(request.getChunkData());

        BasicResponse<Customer> response = new BasicResponse<>();
        response.setContent(customer);

        return response;
    }
}
