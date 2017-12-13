package com.i4m1s1.specmed.service.customer;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.dto.SimpleCustomerDataDTO;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderGetAllSimpleCustomerDataService extends BasicServiceCatch<Object, List<SimpleCustomerDataDTO>> {

    @Autowired
    private CustomerRepository repository;

    /**
     * Metoda usługowa pobierająca podstawowe dane wszystkich pacjentów
     * @param request puste
     * @return Lista wszystkcih pacjentów
     * @throws SMException
     */
    @Override
    protected BasicResponse<List<SimpleCustomerDataDTO>> provide(BasicRequest<Object> request) throws SMException {
        BasicResponse<List<SimpleCustomerDataDTO>> basicDataDTOBasicResponse =new BasicResponse<>();

        List<Customer> customerList = repository.findAll();
        List<SimpleCustomerDataDTO> result = customerList.stream().map(SimpleCustomerDataDTO::new).collect(Collectors.toList());

        basicDataDTOBasicResponse.setContent(result);
        return  basicDataDTOBasicResponse;
    }
}
