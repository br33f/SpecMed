package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.core.Opinion;
import com.i4m1s1.specmed.dto.NewOpinionDTO;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.opinion.ProviderSaveOpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler udostępniajacy API dla usług związanych z ocenami lekarza
 */
@RestController
@RequestMapping(path = "/opinion")
public class OpinionController {

    @Autowired
    private ProviderSaveOpinionService providerSaveOpinionService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<Opinion> getBasicDataList(@RequestBody BasicRequest<NewOpinionDTO> request) {
        return providerSaveOpinionService.serve(request);
    }
}
