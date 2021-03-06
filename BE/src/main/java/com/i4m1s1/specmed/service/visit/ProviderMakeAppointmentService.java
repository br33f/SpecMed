package com.i4m1s1.specmed.service.visit;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.dto.VisitSelectedByCustomerDTO;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderMakeAppointmentService extends BasicServiceCatch<VisitSelectedByCustomerDTO, Visit> {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HttpServletRequest httpRequest;

    @Override
    protected BasicResponse<Visit> provide(BasicRequest<VisitSelectedByCustomerDTO> request) throws SMException {
        Visit visit = visitRepository.findById(request.getChunkData().getVisitId());
        if(visit == null) {
            throw new SMException("20171213042055", WarningMsg.DB_NO_RESULTS);
        }
        User currentUser = AuthHelper.readToken(httpRequest.getHeader(AuthHelper.JWT_HEADER));
        if (currentUser == null) {
            throw new SMException("20180103104104", WarningMsg.MUST_LOGGED);
        }
        Customer customer = customerRepository.findById(currentUser.getEntityId());
        if(customer == null) {
            throw  new SMException("20171213042144", WarningMsg.DB_NO_RESULTS);
        }

        visit.setCustomer(customer);
        visitRepository.save(visit);
        BasicResponse<Visit> response = new BasicResponse<>();
        response.setContent(visit);
        return response;
    }
}
