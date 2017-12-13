package com.i4m1s1.specmed.service.doctor;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.dto.ScheduleDTO;
import com.i4m1s1.specmed.facade.WorkScheduleFacade;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderGetScheduleByIdService extends BasicServiceCatch<String ,List<ScheduleDTO>> {

    @Autowired
    private WorkScheduleFacade facade;

    @Override
    protected BasicResponse<List<ScheduleDTO>> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<List<ScheduleDTO>> response = new BasicResponse<>();
        response.setContent(facade.generateScheduleById(request.getChunkData()));
        return response;
    }
}
