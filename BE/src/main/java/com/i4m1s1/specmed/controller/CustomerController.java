package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.dto.SimpleCustomerDataDTO;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.customer.ProviderGetAllSimpleCustomerDataService;
import com.i4m1s1.specmed.service.doctor.ProviderGetAllDoctorBasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private ProviderGetAllSimpleCustomerDataService serviceAll;

    /**
     * Metoda udostępniająca pobieranie pełnej listy klientów
     * @return Lista klientów
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list/full")
    public BasicResponse<List<SimpleCustomerDataDTO>> getFullList() {
        return serviceAll.serve(null);
    }
}
