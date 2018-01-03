package com.i4m1s1.specmed.service.communication;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.NewPrivateMessageDTO;
import com.i4m1s1.specmed.facade.PrivateMessageOperationsFacade;
import com.i4m1s1.specmed.persistence.PrivateMessage;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSavePrivateMessageFromCustomerService extends BasicServiceCatch<NewPrivateMessageDTO, PrivateMessage> {

    @Autowired
    private PrivateMessageOperationsFacade facade;

    @Override
    protected BasicResponse<PrivateMessage> provide(BasicRequest<NewPrivateMessageDTO> request) throws SMException {
        NewPrivateMessageDTO dto = request.getChunkData();
        PrivateMessage newPM = facade.saveMessageFromCustomer(
                dto.getCustomerId(),
                dto.getMedicalEmployeeId(),
                dto.getContent()
        );
        BasicResponse<PrivateMessage> response = new BasicResponse<>();
        response.setContent(newPM);
        return response;
    }
}
