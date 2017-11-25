package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.service.ProviderDoctorBasicDataService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/medical-employee")
public class MedicalEmployeeController {

    @Autowired
    private ProviderDoctorBasicDataService service;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list/basic")
    public ResponseSM getBasicData(@RequestBody BasicRequest basicRequest) {
        return service.serve(basicRequest);
    }
}
