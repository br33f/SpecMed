package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.service.ProviderGetAllCustomerService;
import com.i4m1s1.specmed.service.ProviderSaveCustomerService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import com.i4m1s1.specmed.dto.SimpleCustomerDataDTO;
import com.i4m1s1.specmed.service.customer.ProviderGetAllSimpleCustomerDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private ProviderGetAllCustomerService providerGetAllCustomerService;

    @Autowired
    private ProviderGetAllSimpleCustomerDataService serviceAll;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Customer> saveCustomer(@RequestBody BasicRequest<Customer> request) {
        return providerSaveCustomerService.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list/full")
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
