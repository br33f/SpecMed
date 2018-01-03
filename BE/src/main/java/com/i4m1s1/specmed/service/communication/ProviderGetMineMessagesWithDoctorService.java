package com.i4m1s1.specmed.service.communication;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.PrivateMessageRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class ProviderGetMineMessagesWithDoctorService extends BasicServiceCatch<String, List<PrivateMessage>> {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    HttpServletRequest httpRequest;

    @Override
    protected BasicResponse<List<PrivateMessage>> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<List<PrivateMessage>> response = new BasicResponse<>();

        User currentUser = AuthHelper.readToken(httpRequest.getHeader(AuthHelper.JWT_HEADER));
        String doctorId = request.getChunkData();

        if (currentUser == null) {
            throw new SMException("20171219124145", WarningMsg.MUST_LOGGED);
        }

        if (doctorId == null) {
            throw new SMException("12171019124145", WarningMsg.NOT_ENOUGH_PARAMS);
        }

        List<PrivateMessage> listOfMineMessagesWithDoctor = privateMessageRepository.findAllByCustomer_IdAndMedicalEmployee_Id(new ObjectId(currentUser.getEntityId()), new ObjectId(doctorId));

        listOfMineMessagesWithDoctor = listOfMineMessagesWithDoctor.stream().sorted(Comparator.comparing(PrivateMessage::getSendTime)).collect(Collectors.toList());

        response.setContent(listOfMineMessagesWithDoctor);
        return response;
    }
}
