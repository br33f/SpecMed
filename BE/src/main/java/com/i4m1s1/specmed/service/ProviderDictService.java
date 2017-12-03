package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.facade.DictionaryOperationFacade;
import com.i4m1s1.specmed.service.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.request.BasicRequest;
import com.i4m1s1.specmed.service.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderDictService extends BasicServiceCatch<String, List<String>> {

    @Autowired
    private DictionaryOperationFacade facade;

    @Override
    protected BasicResponse<List<String>> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<List<String>> response = new BasicResponse<>();
        List<String> dictList = facade.getDictByNameList(request.getChunkData());
        response.setData(dictList);
        return response;
    }
}
