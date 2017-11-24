package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.service.ProviderVisitsByDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/visit")
public class VisitController {

    //test http://localhost:8080/visit/list?id=5a177399c092a813e8ad3ccd
    @Autowired
    private ProviderVisitsByDoctorService service;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ResponseSM getVisitByDoctor(@RequestParam(value = "id") String id) {
        return service.serve(id);
    }
}
