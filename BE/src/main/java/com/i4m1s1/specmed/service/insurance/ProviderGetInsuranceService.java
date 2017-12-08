package com.i4m1s1.specmed.service.insurance;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Insurance;
import com.i4m1s1.specmed.repository.InsuranceRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serwis udostępniający dane wybranego ubezpieczenia
 * Created by br33 on 25.11.2017.
 */
@Service
public class ProviderGetInsuranceService extends BasicServiceCatch<String, Insurance> {

    @Autowired
    private InsuranceRepository repository;

    /**
     * Metoda usługowa pobierająca dane konkretnego ubezpieczenia
     * @param request żądanie zawierające dane konkretnego ubezpieczenia {@link InsuranceRepository }
     * @return Lista danych konkretnego ubezpieczenia
     * @throws SMException
     */
    @Override
    protected BasicResponse<Insurance> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<Insurance> response = new BasicResponse<>();
        response.setContent(repository.findOne(request.getChunkData()));

        return response;
    }
}
