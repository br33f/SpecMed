package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Insurance;
import com.i4m1s1.specmed.service.insurance.ProviderGetInsuranceListService;
import com.i4m1s1.specmed.service.insurance.ProviderGetInsuranceService;
import com.i4m1s1.specmed.service.insurance.ProviderSaveInsuranceService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/insurance")
public class InsuranceController {
    @Autowired
    private ProviderGetInsuranceService providerGetInsuranceService;

    @Autowired
    private ProviderSaveInsuranceService providerSaveInsuranceService;

    @Autowired
    private ProviderGetInsuranceListService providerGetInsuranceListService;


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{insuranceId}")
    public BasicResponse<Insurance> getBasicDataList(@PathVariable("insuranceId") String insuranceId) {
        BasicRequest<String> getInsuranceRequest = new BasicRequest<>();
        getInsuranceRequest.setChunkData(insuranceId);
        return providerGetInsuranceService.serve(getInsuranceRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Insurance> getBasicDataList(@RequestBody BasicRequest<Insurance> request) {
        return providerSaveInsuranceService.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ListResponse<Insurance> getBasicDataList(@RequestBody ListRequest<Insurance> request) {
        return providerGetInsuranceListService.serve(request);
    }
}
