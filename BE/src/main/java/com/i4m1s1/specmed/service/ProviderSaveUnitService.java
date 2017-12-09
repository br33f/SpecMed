package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Unit;
import com.i4m1s1.specmed.repository.UnitRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderSaveUnitService extends BasicServiceCatch<Unit, Unit> {

    @Autowired
    private UnitRepository repository;

    @Override
    protected BasicResponse<Unit> provide(BasicRequest<Unit> request) throws SMException {
        repository.save(request.getChunkData());
        BasicResponse<Unit> response = new BasicResponse<>();
        response.setContent(request.getChunkData());
        return response;
    }
}
