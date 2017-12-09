package com.i4m1s1.specmed.service.probe;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.repository.ProbeRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveProbeService extends BasicServiceCatch<Probe, Probe>{

    @Autowired
    private ProbeRepository repository;


    @Override
    protected BasicResponse<Probe> provide(BasicRequest<Probe> request) throws SMException {
        repository.save(request.getChunkData());
        BasicResponse<Probe> response = new BasicResponse<>();
        response.setContent(request.getChunkData());
        return response;
    }
}
