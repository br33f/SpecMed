package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.VisitBasicDataDTO;
import com.i4m1s1.specmed.dto.VisitDTO;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.service.ProviderGetFreeVisitService;
import com.i4m1s1.specmed.service.visit.ProviderSaveVisitService;
import com.i4m1s1.specmed.service.visit.ProviderVisitsByDoctorService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
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
 * Kontroler udostępniajacy API dla usług związanych z wizytą
 */
@RestController
@RequestMapping(path = "/visit")
public class VisitController {

    @Autowired
    private ProviderVisitsByDoctorService providerVisitsByDoctorService;

    @Autowired
    private ProviderSaveVisitService providerSavevisitService;

    @Autowired
    private ProviderGetFreeVisitService providerGetFreeVisitService;
    /**
     * Metoda udostępniająca wyszukanie listy danych wizyt
     * @param request żądanie zawierające listę danych wizyt
     * @return Lista danych wizyt
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ListResponse<VisitBasicDataDTO> getVisitByDoctor(@RequestBody ListRequest<Visit> request) {
        return providerVisitsByDoctorService.serve(request);
    }

    /**
     * Metoda udostępniajaca dodawanie/edycję nowej wizyty
     * @param request żądanie zawierające dane wizyty do dodania
     * @return Dane wizyty dodawanej/edytowanej
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<VisitDTO> saveVisit(@RequestBody BasicRequest<VisitDTO> request) {
        return providerSavevisitService.serve(request);
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list/free")
    public BasicResponse<List<VisitBasicDataDTO>> getFreeVisitByDoctorId(@RequestBody BasicRequest<String> request) {
        return providerGetFreeVisitService.serve(request);
    }
}
