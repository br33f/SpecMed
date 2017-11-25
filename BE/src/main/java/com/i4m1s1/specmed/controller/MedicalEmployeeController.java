package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.service.ProviderDoctorBasicDataService;
import com.i4m1s1.specmed.service.request.EmptyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/medical-employee")
public class MedicalEmployeeController {

    @Autowired
    private ProviderDoctorBasicDataService service;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list/basic")
    public ResponseSM getBasicData(EmptyRequest emptyRequest) {
           return service.serve(emptyRequest);
    }
}
