package com.i4m1s1.specmed.controller;

import com.i4m1s1.specmed.dto.NewPrivateMessageDTO;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.service.communication.ProviderGetMineMessageTargetsService;
import com.i4m1s1.specmed.service.communication.ProviderGetMineMessagesWithDoctorService;
import com.i4m1s1.specmed.service.communication.ProviderSavePrivateMessageFromCustomerService;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@RestController
@RequestMapping(path = "/communication")
public class CommunicationController {

    @Autowired
    private ProviderSavePrivateMessageFromCustomerService savePrivateMessageService;

    @Autowired
    private ProviderGetMineMessageTargetsService getMineMessageTargetsService;

    @Autowired
    private ProviderGetMineMessagesWithDoctorService getMineMessagesWithDoctorService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/save")
    public BasicResponse<PrivateMessage> savePrivateMessage(@RequestBody BasicRequest<NewPrivateMessageDTO> request) {
        return savePrivateMessageService.serve(request);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path="/mine/targets")
    public BasicResponse<List<MedicalEmployee>> getMineMessageTargets () {
        return getMineMessageTargetsService.serve(null);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path="/mine/with/{doctorId}")
    public BasicResponse<List<PrivateMessage>> getMineMessagesWithDoctor (@PathVariable("doctorId") String doctorId) {
        BasicRequest<String> getMineMessagesWithDoctorRequest = new BasicRequest<>();
        getMineMessagesWithDoctorRequest.setChunkData(doctorId);
        return getMineMessagesWithDoctorService.serve(getMineMessagesWithDoctorRequest);
    }
}
