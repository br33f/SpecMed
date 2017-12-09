package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.persistence.MedicalOrder;
import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.persistence.Procedure;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.medicalorder.ProviderSaveMedicalOrderProbeService;
import com.i4m1s1.specmed.service.medicalorder.ProviderSaveMedicalOrderProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler udostępniajacy API dla usług związanych z usługą medyczną
 */
@RestController
@RequestMapping(path = "/medical-order")
public class MedicalOrderController {

    @Autowired
    private ProviderSaveMedicalOrderProbeService providerSaveMedicalOrderProbeService;

    @Autowired
    private ProviderSaveMedicalOrderProcedureService providerSaveMedicalOrderProcedureService;

    /** Przyklad:

     {
        "chunkData": {
            "id": "123",
            "action": {
                "id":"432"
                }
        }
     }
     z tego action utworzy się probe
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/new-probe")
    public BasicResponse<MedicalOrder<Probe>> saveMedicalOrderProbe(@RequestBody BasicRequest<MedicalOrder<Probe>> request) {
        return providerSaveMedicalOrderProbeService.serve(request);
    }


    /** Przyklad:

     {
        "chunkData": {
            "id": "123",
            "action": {
                "id":"432"
            }
        }
     }
     z tego action utworzy się procedure! RÓŻNICA JEST W URL (/new-xxx)
     */

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/new-procedure")
    public BasicResponse<MedicalOrder<Procedure>> saveMedicalOrderProcedure(@RequestBody BasicRequest<MedicalOrder<Procedure>> request) {
        return providerSaveMedicalOrderProcedureService.serve(request);
    }
}
