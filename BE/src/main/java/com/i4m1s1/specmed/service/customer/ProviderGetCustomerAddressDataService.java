package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderGetCustomerAddressDataService extends BasicServiceCatch<String, AddressData> {

    @Autowired
    private CustomerRepository repository;

    @Override
    protected BasicResponse<AddressData> provide(BasicRequest<String> request) throws SMException {
        Customer customer = repository.findById(request.getChunkData());

        BasicResponse<AddressData> response = new BasicResponse<>();
        response.setContent(customer.getAddressData());

        return response;
    }
}
