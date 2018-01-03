package com.i4m1s1.specmed.service.opinion;

import com.i4m1s1.specmed.core.Opinion;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.dto.NewOpinionDTO;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.VisitRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Service
public class ProviderSaveOpinionService extends BasicServiceCatch<NewOpinionDTO, Opinion> {

    @Autowired
    private VisitRepository repository;

    @Override
    protected BasicResponse<Opinion> provide(BasicRequest<NewOpinionDTO> request) throws SMException {
        NewOpinionDTO dto = request.getChunkData();
        Visit visit = repository.findById(dto.getVisitId());
        if(visit == null) {
            throw new SMException("20171209052501", WarningMsg.VISIT_FOR_OPINION_NOT_FOUND);
        }
        Opinion newOpinion = dto.createOpinion();
        visit.setOpinion(newOpinion);
        repository.save(visit);

        BasicResponse<Opinion> response = new BasicResponse<>();
        response.setContent(newOpinion);
        return response;
    }
}
