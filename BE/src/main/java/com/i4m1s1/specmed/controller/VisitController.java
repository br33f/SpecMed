package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.service.ProviderVisitsByDoctorService;
import com.i4m1s1.specmed.service.request.ListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/visit")
public class VisitController {

    @Autowired
    private ProviderVisitsByDoctorService service;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ResponseSM getVisitByDoctor(@RequestBody ListRequest listRequest) {
        return service.serve(listRequest);
    }
}
