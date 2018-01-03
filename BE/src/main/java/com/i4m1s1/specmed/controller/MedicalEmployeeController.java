package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.dto.ScheduleDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import com.i4m1s1.specmed.service.doctor.*;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeeAddressDataService;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeeContactDataService;
import com.i4m1s1.specmed.service.employee.ProviderGetEmployeePersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler udostępniajacy API dla usług związanych z pracownikiem medycznym
 */
@RestController
@RequestMapping(path = "/medical-employee")
public class MedicalEmployeeController {

    @Autowired
    private ProviderDoctorBasicDataService service;

    @Autowired
    private ProviderGetAllDoctorBasicDataService serviceAll;

    @Autowired
    private ProviderMedicalEmployeeDataService serviceAllPost;

    @Autowired
    private ProviderSaveMedicalEmployeeService serviceSave;

    @Autowired
    private ProviderGetScheduleByIdService serviceSchedule;

    @Autowired
    private ProviderGetDoctorBasicDataBySpecializationService providerGetDoctorBasicDataBySpecializationService;

    @Autowired
    private ProviderGetDoctorAddressDataService providerGetDoctorAddressDataService;

    @Autowired
    private ProviderGetDoctorContactDataService providerGetDoctorContactDataService;

    @Autowired
    private ProviderGetDoctorPersonalDataService providerGetDoctorPersonalDataService;

    @Autowired
    private ProviderGetDoctorSpecializationService providerGetDoctorSpecializationService;

    /**
     * Metoda udostępniająca wyszukania konkretnych pracowników medycznych
     * @param request żądanie zawierające listę danych pracowników medycznych
     * @return Lista danych pracowników medycznych
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list/basic")
    public ListResponse<DoctorBasicDataDTO> getBasicData(@RequestBody ListRequest<MedicalEmployee> request) {
        return service.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/specialization/list")
    public BasicResponse<List<DoctorBasicDataDTO>> getBasicData(@RequestBody BasicRequest<String> request) {
        return providerGetDoctorBasicDataBySpecializationService.serve(request);
    }

    /**
     * Metoda udostępniająca pobieranie pełnej listy pracowników medycznych
     * @return Lista danych pracowników medycznych
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list/full")
    public BasicResponse<List<DoctorBasicDataDTO>> getFullList() {
        return serviceAll.serve(null);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list/full")
    public ListResponse<MedicalEmployee> getPostFullList(@RequestBody ListRequest<MedicalEmployee> request) {
        return serviceAllPost.serve(request);
    }

    /**
     *
     * @param request
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<MedicalEmployee> saveProbe(@RequestBody BasicRequest<MedicalEmployee> request) {
        return serviceSave.serve(request);
    }

    /**
     * Metoda zwracająca grafik w postaci Listy obiektów typu {@link com.i4m1s1.specmed.dto.ScheduleDTO}
     * @param request żądanie zawierające id pracownika medycznego
     * @return Lista obiektów typu DTO
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/schedule")
    public BasicResponse<List<ScheduleDTO>> getDictByName(@RequestBody BasicRequest<String> request) {
        return serviceSchedule.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/address")
    public BasicResponse<AddressData> getDoctorAddressData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetDoctorAddressDataService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/contact")
    public BasicResponse<ContactData> getDoctorContactData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetDoctorContactDataService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/personal")
    public BasicResponse<PersonalData> getDoctorPersonalData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetDoctorPersonalDataService.serve(getEmployeeRequest);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{employeeId}/specialization")
    public BasicResponse<List<String>> getDoctorSpecializationData(@PathVariable("employeeId") String employeeId) {
        BasicRequest<String> getEmployeeRequest = new BasicRequest<>();
        getEmployeeRequest.setChunkData(employeeId);
        return providerGetDoctorSpecializationService.serve(getEmployeeRequest);
    }

}
