package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.facade.DictionaryOperationFacade;
import com.i4m1s1.specmed.service.response.ProviderDictResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderDictService extends ServiceCatch<ProviderDictResponse, String> {

    @Autowired
    private DictionaryOperationFacade facade;

    @Override
    public ProviderDictResponse provide(String s) throws SMException {

        List<String> dictList = facade.getDictByNameList(s);
        return new ProviderDictResponse(dictList);
    }
}
