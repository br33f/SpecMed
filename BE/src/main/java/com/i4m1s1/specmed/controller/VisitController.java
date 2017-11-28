package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.VisitBasicDataDTO;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.service.ProviderVisitsByDoctorService;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.ListResponse;
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
    public ListResponse<VisitBasicDataDTO> getVisitByDoctor(@RequestBody ListRequest<Visit> request) {
        return service.serve(request);
    }
}
