package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Insurance;
import com.i4m1s1.specmed.service.insurance.ProviderGetInsuranceListService;
import com.i4m1s1.specmed.service.insurance.ProviderGetInsuranceService;
import com.i4m1s1.specmed.service.insurance.ProviderSaveInsuranceService;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler udostępniajacy API dla usług związanych z ubezpieczeniami
 */
@RestController
@RequestMapping(path = "/insurance")
public class InsuranceController {
    @Autowired
    private ProviderGetInsuranceService providerGetInsuranceService;

    @Autowired
    private ProviderSaveInsuranceService providerSaveInsuranceService;

    @Autowired
    private ProviderGetInsuranceListService providerGetInsuranceListService;

    /**
     * Metoda zawierająca wyszukiwanie danych ubezpieczneia po id
     * @param insuranceId żądanie zawierające id ubezpieczenia
     * @return dane ubezpieczenia
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/get/{insuranceId}")
    public BasicResponse<Insurance> getBasicDataList(@PathVariable("insuranceId") String insuranceId) {
        BasicRequest<String> getInsuranceRequest = new BasicRequest<>();
        getInsuranceRequest.setChunkData(insuranceId);
        return providerGetInsuranceService.serve(getInsuranceRequest);
    }

    /**
     * metoda udostępniająca dodawanie nowego ubezpieczenia oraz edycję
     * @param request żądanie zawierające dane ubezpieczenia dodawanego/edytowanego
     * @return Dane ubezpieczenia
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Insurance> getBasicDataList(@RequestBody BasicRequest<Insurance> request) {
        return providerSaveInsuranceService.serve(request);
    }

    /**
     * Metoda udostępniająca wyszukanie pełnej listy danych ubezpieczeń
     * @param request żądanie zawierające dane listy ubezpieczeń
     * @return Lista danych ubezpieczeń
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/list")
    public ListResponse<Insurance> getBasicDataList(@RequestBody ListRequest<Insurance> request) {
        return providerGetInsuranceListService.serve(request);
    }
}
