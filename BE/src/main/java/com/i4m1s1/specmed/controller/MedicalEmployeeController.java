package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.service.doctor.ProviderDoctorBasicDataService;
import com.i4m1s1.specmed.service.doctor.ProviderGetAllDoctorBasicDataService;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Metoda udostępniająca pobieranie pełnej listy pracowników medycznych
     * @return Lista danych pracowników medycznych
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/list/full")
    public BasicResponse<List<DoctorBasicDataDTO>> getFullList() {
        return serviceAll.serve(null);
    }
}
