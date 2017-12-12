package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.service.common.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderGetAllCustomerService extends ListServiceCatch<Customer, Customer> {

    @Autowired
    private CustomerRepository repository;

    @Override
    protected ListResponse<Customer> provide(ListRequest<Customer> request) throws SMException {
        Customer customerRequest = request.getSearchCriteria();
        Example<Customer> customerExample = Example.of(customerRequest);
        Page<Customer> page = repository.findAll(customerExample, request.getPageCriteria().getAsPageRequest());
        return new ListResponse<>(page);
    }
}
