package com.i4m1s1.specmed.service.auth;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.BindUserToCustomerDTO;
import com.i4m1s1.specmed.facade.BindingUserAccOperationFacade;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.i4m1s1.specmed.core.helper.CommonHelper.checkNotNull;

/**
 * Bindujemy w ten sposób bo wg mnie sam PESEL to mało zeby zbindować sobie konto.
 * W tym przypadku trzeba jeszcze dostać maila i wbic w link (z id customera)
 * To id można jakos haszować.
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderBindUserAccToCustomerService extends BasicServiceCatch<BindUserToCustomerDTO, Customer> {

    @Autowired
    private BindingUserAccOperationFacade facade;

    @Override
    protected BasicResponse<Customer> provide(BasicRequest<BindUserToCustomerDTO> request) throws SMException {
        BindUserToCustomerDTO dto = request.getChunkData();
        Customer customer = facade.bindAccountIfExists(
                dto.getUserEmail(),
                dto.getUserPassword(),
                dto.getPesel(),
                dto.getCustomerId()
        );
        BasicResponse<Customer> response = new BasicResponse<>();
        response.setContent(customer);
        return response;
    }

    @Override
    protected void validate() {
        isValid = isValid && checkNotNull(request);
        isValid = isValid && checkNotNull(request.getChunkData());
    }
}
