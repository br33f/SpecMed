package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeeAddressDataService;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeeContactDataService;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeePersonalDataService;
import com.i4m1s1.specmed.service.employee.ProviderEmployeeBasicDataService;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeeService;
import com.i4m1s1.specmed.service.employee.ProviderSaveEmployeeService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler udostępniajacy API dla usług związanych z pracownikiem
 */
@RestController
@RequestMapping(path ="/employee")
public class EmployeeController {
    @Autowired
    private ProviderEmployeeBasicDataService providerEmployeeBasicDataService;

    @Autowired
    private ProviderGetEmployeeService providerGetEmployeeService;

    @Autowired
    private ProviderGetEmployeeAddressDataService providerGetEmployeeAddressDataService;

    @Autowired
    private ProviderGetEmployeeContactDataService providerGetEmployeeContactDataService;

    @Autowired
    private ProviderGetEmployeePersonalDataService providerGetEmployeePersonalDataService;

    @Autowired
    private ProviderSaveEmployeeService providerSaveEmployeeService;

    /**
     * Metoda zapewniająca pobranie danych konkretnego pracownika
     * @param employeeId żądanie zawierające id pracownika
     * @return Dane pracownika
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}")
    public BasicResponse<Employee> getBasicDataList(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetEmployeeService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/address")
    public BasicResponse<AddressData> getCustomerAddressData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetEmployeeAddressDataService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/contact")
    public BasicResponse<ContactData> getCustomerContactData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetEmployeeContactDataService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/personal")
    public BasicResponse<PersonalData> getCustomerPersonalData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetEmployeePersonalDataService.serve(getEmployeeRequest);
    }

    /**
     * Metoda zapewniająca dodanie/edycję nowego pracownika
     * @param request żądanie zawierające dane pracownika dodawanego/edytowanego
     * @return dane pracownika dodawanego/edytowanego
     */
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

    /**
     * Metoda zapewniająca zwracanie listy wszystkich pracowników
     * @param request żądanie zawierające dane pracowników
     * @return Lista danych pracowników
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ListResponse<Employee> getBasicDataList(@RequestBody ListRequest<Employee> request) {
        return providerEmployeeBasicDataService.serve(request);
    }

}
