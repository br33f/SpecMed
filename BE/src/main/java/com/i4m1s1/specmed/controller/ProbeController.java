package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.probe.ProviderSaveProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler udostępniajacy API dla usług związanych z badaniami
 */
@RestController
@RequestMapping(path = "/probe")
public class ProbeController {

    @Autowired
    private ProviderSaveProbeService providerSaveProbeService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Probe> saveProbe(@RequestBody BasicRequest<Probe> request) {
        return providerSaveProbeService.serve(request);
    }
}
