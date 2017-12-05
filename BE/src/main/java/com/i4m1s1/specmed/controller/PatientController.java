package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Patient;
import com.i4m1s1.specmed.service.ProviderSaveMedicalPacketService;
import com.i4m1s1.specmed.service.ProviderSavePatientService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/medical-packet")
public class PatientController {
    @Autowired
    private ProviderSavePatientService providerSavePatientService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Patient> getBasicDataList(@RequestBody BasicRequest<Patient> request) {
        return providerSavePatientService.serve(request);
    }
}