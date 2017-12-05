package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSaveVisitService extends BasicServiceCatch<Visit, Visit> {

    @Autowired
    private VisitRepository repository;

    @Override
    protected BasicResponse<Visit> provide(BasicRequest<Visit> request) throws SMException {
        Visit savedVisit = repository.save(request.getChunkData());

        BasicResponse<Visit> response = new BasicResponse<>();
        response.setContent(savedVisit);
        return response;
    }

}
