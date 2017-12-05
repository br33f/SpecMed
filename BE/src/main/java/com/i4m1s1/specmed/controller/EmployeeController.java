package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.service.ProviderEmployeeBasicDataService;
import com.i4m1s1.specmed.service.ProviderGetEmployeeService;
import com.i4m1s1.specmed.service.ProviderSaveEmployeeService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/employee")
public class EmployeeController {
    @Autowired
    private ProviderEmployeeBasicDataService providerEmployeeBasicDataService;

    @Autowired
    private ProviderGetEmployeeService providerGetEmployeeService;

    @Autowired
    private ProviderSaveEmployeeService providerSaveEmployeeService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}")
    public BasicResponse<Employee> getBasicDataList(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetEmployeeService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Employee> getBasicDataList(@RequestBody BasicRequest<Employee> request) {
        return providerSaveEmployeeService.serve(request);
    }

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
    public ListResponse<Employee> getBasicDataList(@RequestBody ListRequest<Employee> request) {
        return providerEmployeeBasicDataService.serve(request);
    }

}
