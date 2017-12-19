package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.NewPrivateMessageDTO;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.service.ProviderSavePrivateMessageFromCustomerService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RestController
@RequestMapping(path = "/communication")
public class CommunicationController {

    @Autowired
    private ProviderSavePrivateMessageFromCustomerService savePrivateMessageService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<PrivateMessage> savePrivateMessage(@RequestBody BasicRequest<NewPrivateMessageDTO> request) {
        return savePrivateMessageService.serve(request);
    }
}
