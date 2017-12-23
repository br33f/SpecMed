package com.i4m1s1.specmed.service.unit;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Unit;
import com.i4m1s1.specmed.repository.UnitRepository;
import com.i4m1s1.specmed.service.common.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderGetAllUnitService extends ListServiceCatch<Unit, Unit> {

    @Autowired
    private UnitRepository repository;

    @Override
    protected ListResponse<Unit> provide(ListRequest<Unit> request) throws SMException {
        Page<Unit> page = repository.findAll(request.getPageCriteria().getAsPageRequest());
        ListResponse<Unit> response = new ListResponse<>(page);
        return response;
    }
}
