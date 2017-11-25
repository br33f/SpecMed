package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.initmodules.OnStartInsertData;
import com.i4m1s1.specmed.service.ProviderDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/common")
public class CommonController {

    @Autowired
    private ProviderDictService service;

    @Autowired
    private OnStartInsertData initModule;

    //Test: http://localhost:8080/common/dict?name=VISIT_STATUS
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/dict")
    public ResponseSM getDictByName(@RequestParam("name") String name) {
        return service.serve(name);
    }

    //DO ODPALANIA INIT MODULOW
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/init-module")
    public ResponseSM initModuleStart() {
        initModule.zapelnijSlowniki();
        initModule.initMedicalEmpoyeeAndVisit();
        initModule.initEmployees();
        return ResponseSM.wrap("OK!", null);
    }


}
