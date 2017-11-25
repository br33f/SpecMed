package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.service.ProviderEmployeeBasicDataService;
import com.i4m1s1.specmed.service.request.ListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/employee")
public class EmployeeController {
    @Autowired
    private ProviderEmployeeBasicDataService providerEmployeeBasicDataService;

    /*
     * Usługa do podpięcia pod paginowalne/sortowalne/wyszukiwalne tabele, listy itp.
     *
     * Powinna przyjmować wspólny response "ListRequest",
     * natomiast rzucać ListResponse - wtedy ładnie zintegruje się z komponentem tabeli z Reacta.
     *
     * Przykładowe żądanie:
     *
     {
        "pageCriteria": {
            "currentPage": "0",
            "pageSize": "10"
        },
        "searchCriteria": {
            "name": "Zbigniew"
        }
     }
     *
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ResponseSM getBasicDataList(@RequestBody ListRequest request) {
        return providerEmployeeBasicDataService.serve(request);
    }

}
