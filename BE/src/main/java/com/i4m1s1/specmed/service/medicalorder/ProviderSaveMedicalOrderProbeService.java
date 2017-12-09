package com.i4m1s1.specmed.service.medicalorder;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.MedicalOrder;
import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.repository.MedicalOrderRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveMedicalOrderProbeService extends BasicServiceCatch<MedicalOrder<Probe>, MedicalOrder<Probe>>{
    @Autowired
    private MedicalOrderRepository repository;

    @Override
    protected BasicResponse<MedicalOrder<Probe>> provide(BasicRequest<MedicalOrder<Probe>> request) throws SMException {
        repository.save(request.getChunkData());
        BasicResponse<MedicalOrder<Probe>> response = new BasicResponse<>();
        response.setContent(request.getChunkData());
        return response;
    }
}
