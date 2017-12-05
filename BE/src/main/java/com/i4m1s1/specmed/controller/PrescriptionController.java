package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Prescription;
import com.i4m1s1.specmed.service.ProviderSavePrescriptionService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/prescription")
public class PrescriptionController {
    @Autowired
    private ProviderSavePrescriptionService providerSavePrescriptionService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Prescription> getBasicDataList(@RequestBody BasicRequest<Prescription> request) {
        return providerSavePrescriptionService.serve(request);
    }
}
