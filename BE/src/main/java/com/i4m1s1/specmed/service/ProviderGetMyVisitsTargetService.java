package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.dto.VisitDTO;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service

public class ProviderGetMyVisitsTargetService extends BasicServiceCatch<Object, List<VisitDTO>> {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private HttpServletRequest httpRequest;

    @Override
    protected BasicResponse<List<VisitDTO>> provide(BasicRequest<Object> request) throws SMException {
        BasicResponse<List<VisitDTO>> response = new BasicResponse<>();
        User currentUser = AuthHelper.readToken(httpRequest.getHeader(AuthHelper.JWT_HEADER));
        if (currentUser == null) {
            throw new SMException("20180103104104", WarningMsg.MUST_LOGGED);
        }

        List<Visit> visits = visitRepository.findAll();
//        List<Visit> visits = visitRepository.findAllByCustomerId(currentUser.getEntityId());
        List<VisitDTO> dtos = new ArrayList<>();
        for (Visit v : visits) {
            if (v.getCustomer() != null && v.getCustomer().getId().equals(currentUser.getEntityId())) {
                dtos.add(new VisitDTO(v));
            }
        }
        response.setContent(dtos);
        return response;
    }
}
