package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Unit;
import com.i4m1s1.specmed.service.unit.ProviderGetAllUnitService;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import com.i4m1s1.specmed.service.unit.ProviderSaveUnitService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RestController
@RequestMapping(path = "/unit")
public class UnitController {

    @Autowired
    private ProviderSaveUnitService providerSaveUnitService;

    @Autowired
    private ProviderGetAllUnitService getAllUnitService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Unit> saveUnit(@RequestBody BasicRequest<Unit> request) {
        return providerSaveUnitService.serve(request);
    }

    /**
     * Metoda zapewniająca zwracanie listy wszystkich pracowników
     * @param request żądanie zawierające dane pracowników
     * @return Lista danych pracowników
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ListResponse<Unit> getBasicDataList(@RequestBody ListRequest<Unit> request) {
        return getAllUnitService.serve(request);
    }
}