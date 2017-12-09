package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Procedure;
import com.i4m1s1.specmed.repository.ProcedureRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveProcedureService extends BasicServiceCatch<Procedure, Procedure> {

    @Autowired
    private ProcedureRepository repository;

    @Override
    protected BasicResponse<Procedure> provide(BasicRequest<Procedure> request) throws SMException {
        repository.save(request.getChunkData());
        BasicResponse<Procedure> response = new BasicResponse<>();
        response.setContent(request.getChunkData());
        return response;
    }
}
