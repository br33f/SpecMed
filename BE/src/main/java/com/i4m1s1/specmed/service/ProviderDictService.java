package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.enums.DictionaryNames;
import com.i4m1s1.specmed.core.enums.WarningMsg;
import com.i4m1s1.specmed.core.enums.persistence.DictionarySM;
import com.i4m1s1.specmed.initmodules.OnStartInsertData;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Service
public class ProviderDictService implements ServiceSM<Map<Integer, String>, String> {
    @Autowired
    private DictionaryRepository repository;
    @Autowired
    private OnStartInsertData init;

    @Override
    public Map<Integer, String> provide(String s) throws SMException {
//        init.zapelnijSlowniki();
        Map<Integer, String> result = null;
        DictionaryNames name = DictionaryNames.valueOf(s);
        DictionarySM fromDB = repository.findByDictionaryName(name);
        if(fromDB == null) {
            throw new SMException("20171124050728", WarningMsg.DB_NO_RESULTS);
        }
        return fromDB.getDictMap();
    }
}
