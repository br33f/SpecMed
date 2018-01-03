package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.PrescriptionDTO;
import com.i4m1s1.specmed.persistence.Prescription;
import com.i4m1s1.specmed.service.prescription.ProviderSavePrescriptionService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler udostępniajacy API dla usług związanych z receptami
 */
@RestController
@RequestMapping(path = "/prescription")
public class PrescriptionController {
    @Autowired
    private ProviderSavePrescriptionService providerSavePrescriptionService;

    /**
     * Metoda udostępniająca dodawanie/edycję recept
     * @param request żądanie zawierające dane recepty
     * @return Dane dodawanej/edytowanej recepty
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Prescription> savePrescription(@RequestBody BasicRequest<PrescriptionDTO> request) {
        return providerSavePrescriptionService.serve(request);
    }
}
