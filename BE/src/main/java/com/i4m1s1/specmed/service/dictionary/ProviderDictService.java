package com.i4m1s1.specmed.service.dictionary;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.facade.DictionaryOperationFacade;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Serwis dostarczajacy aktualne slowniki
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Service
public class ProviderDictService extends BasicServiceCatch<String, Map<Integer, String>> {

    @Autowired
    private DictionaryOperationFacade facade;

    /**
     * Metoda usługowa pobierająca słowniki
     * @param request żądanie zawierające dane o slownikach {@link DictionaryOperationFacade }
     * @return Listę danych zawartych w sklowniku
     * @throws SMException
     */
    @Override
    protected BasicResponse<Map<Integer, String>> provide(BasicRequest<String> request) throws SMException {
        BasicResponse<Map<Integer, String>> response = new BasicResponse<>();
        Map<Integer, String> dictMap = facade.getDictByNameMap(request.getChunkData());
        response.setContent(dictMap);
        return response;
    }
}
