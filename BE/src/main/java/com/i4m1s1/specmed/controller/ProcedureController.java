package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Procedure;
import com.i4m1s1.specmed.service.procedure.ProviderSaveProcedureService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler udostępniajacy API dla usług związanych z zabiegiem
 */
@RestController
@RequestMapping(path = "/procedure")
public class ProcedureController {

    @Autowired
    private ProviderSaveProcedureService providerSaveProcedureService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Procedure> saveProbe(@RequestBody BasicRequest<Procedure> request) {
        return providerSaveProcedureService.serve(request);
    }
}
