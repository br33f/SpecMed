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
 * Serwis udostępniający dodanie ubezpieczenia
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSaveInsuranceService extends BasicServiceCatch<Insurance, Insurance> {

    @Autowired
    private InsuranceRepository repository;

    /**
     * Metoda usługowa dodająca dane ubezpieczenia
     * @param request żądanie zawierające dane ubezpieczenia do dodania {@link InsuranceRepository }
     * @return Lista danych dodanego ubezpieczenia
     * @throws SMException
     */
    @Override
    protected BasicResponse<Insurance> provide(BasicRequest<Insurance> request) throws SMException {
        BasicResponse<Insurance> response = new BasicResponse<>();
        response.setContent(repository.save(request.getChunkData()));

        return response;
    }
}
