package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.initmodules.OnStartInsertData;
import com.i4m1s1.specmed.service.ProviderDictService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Kontroler udostępniający API dla wspólnych usług
 */
@RestController
@RequestMapping(path = "/common")
public class CommonController {

    @Autowired
    private ProviderDictService service;

    @Autowired
    private OnStartInsertData initModule;

    /**
     * Metoda zapewnijaca wyszukiwanie słownika
     * @param request żądanie zawierające nazwę słownika
     * @return Lista danych słownika
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/dict")
    public BasicResponse<Map<Integer, String>> getDictByName(@RequestBody BasicRequest<String> request) {
        return service.serve(request);
    }

    /**
     * Metoda zapewniająca gaszenie modułów startowych
     * @return potwierdzenie wykonania metody
     */
    //DO GASZENIA INIT MODULOW
    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, path = "/module/stop")
    public String initModuleStop() {
        initModule.destroyAllModules();
        return "debug - delete - ok";
    }

    /**
     * Metoda zapewniająca uruchomienie modułów startowych
     * @return potwierdzenie uruchomienia modułów
     */
    //DO ODPALENIA INIT MODULOW
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/module/start")
    public String initModuleStart() {
        initModule.zapelnijSlowniki();
        initModule.initMedicalEmpoyeeAndVisit();
        initModule.initEmployees();
        return "debug - init - ok";
    }

}
