package com.i4m1s1.specmed.controller;


import com.i4m1s1.specmed.persistence.Prescription;
import com.i4m1s1.specmed.service.ProviderSaveMedicalPacketService;
import com.i4m1s1.specmed.service.ProviderSavePrescriptionService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/medical-packet")
public class MedicalPacket {
    @Autowired
    private ProviderSaveMedicalPacketService providerSaveMedicalPacketService;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<MedicalPacket> getBasicDataList(@RequestBody BasicRequest<MedicalPacket> request) {
        return providerSaveMedicalPacketService.serve(request);
    }
}

