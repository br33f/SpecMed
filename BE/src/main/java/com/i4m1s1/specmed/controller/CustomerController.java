package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.service.customer.*;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.i4m1s1.specmed.dto.SimpleCustomerDataDTO;

import java.util.List;

/**
 * Kontroler udostępniajacy API dla usług związanych z klietnem
 */
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private ProviderSaveCustomerService providerSaveCustomerService;

    @Autowired
    private ProviderGetCustomerService providerGetCustomerService;

    @Autowired
    private ProviderGetCustomerAddressDataService providerGetCustomerAddressDataService;

    @Autowired
    private ProviderGetCustomerContactDataService providerGetCustomerContactDataService;

    @Autowired
    private ProviderGetCustomerPersonalDataService providerGetCustomerPersonalDataService;

    @Autowired
    private ProviderGetAllCustomerService providerGetAllCustomerService;

    @Autowired
    private ProviderGetAllSimpleCustomerDataService serviceAll;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Customer> saveCustomer(@RequestBody BasicRequest<Customer> request) {
        return providerSaveCustomerService.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{customerId}")
    public BasicResponse<Customer> getCustomer(@PathVariable("customerId") String customerId) {
        BasicRequest<String> getCustomerRequest = new BasicRequest<>();
        getCustomerRequest.setChunkData(customerId);
        return providerGetCustomerService.serve(getCustomerRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{customerId}/address")
    public BasicResponse<AddressData> getCustomerAddressData(@PathVariable("customerId") String customerId) {
        BasicRequest<String> getCustomerRequest = new BasicRequest<>();
        getCustomerRequest.setChunkData(customerId);
        return providerGetCustomerAddressDataService.serve(getCustomerRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{customerId}/contact")
    public BasicResponse<ContactData> getCustomerContactData(@PathVariable("customerId") String customerId) {
        BasicRequest<String> getCustomerRequest = new BasicRequest<>();
        getCustomerRequest.setChunkData(customerId);
        return providerGetCustomerContactDataService.serve(getCustomerRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{customerId}/personal")
    public BasicResponse<PersonalData> getCustomerPersonalData(@PathVariable("customerId") String customerId) {
        BasicRequest<String> getCustomerRequest = new BasicRequest<>();
        getCustomerRequest.setChunkData(customerId);
        return providerGetCustomerPersonalDataService.serve(getCustomerRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ListResponse<Customer> getBasicDataList(@RequestBody ListRequest<Customer> request) {
        return providerGetAllCustomerService.serve(request);
    }

    /**
     * Metoda udostępniająca pobieranie pełnej listy klientów
     * @return Lista klientów
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list/simple/full")
    public BasicResponse<List<SimpleCustomerDataDTO>> getFullList() {
        return serviceAll.serve(null);
    }
}
