package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.dict.persistence.DictionarySM;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Fasada udostępniająca operacje biznesow dla słowników
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

@Component
public class DictionaryOperationFacade {

    @Autowired
    private DictionaryRepository repository;

    public Map<Integer, String> getDictByNameMap(DictionaryNames name) throws SMException {
        boolean existsInDB = checkIfExistAsDictionaryName(name.toString());
        if (!existsInDB) {
            throw new SMException("20171125061106", WarningMsg.DICTIONARY_NOT_FOUND, name.toString());
        }
        DictionarySM dict = repository.findByDictionaryName(name);
        return dict.getDictMap();
    }

    public List<String> getDictByNameList(DictionaryNames name) throws SMException {
        Map<Integer, String> map = getDictByNameMap(name);
        return new ArrayList<>(map.values());
    }

    public Map<Integer, String> getDictByNameMap(String name) throws SMException {
        boolean existsInDB = checkIfExistAsDictionaryName(name);
        if (!existsInDB) {
            throw new SMException("20171125061106", WarningMsg.DICTIONARY_NOT_FOUND, name);
        }
        DictionarySM dict = repository.findByDictionaryName(DictionaryNames.valueOf(name));
        return dict.getDictMap();
    }

    public List<String> getDictByNameList(String name) throws SMException {
        Map<Integer, String> map = getDictByNameMap(name);
        return new ArrayList<>(map.values());
    }

    public boolean checkIfExistsInDictionary(DictionaryNames name, String toCheck) throws SMException {
        if (getIdByNameInDictionary(name, toCheck) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getIdByNameInDictionary(DictionaryNames name, String toCheck) throws SMException {
        Map<Integer, String> map = getDictByNameMap(name);
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            if (e.getValue().equals(toCheck)) {
                return e.getKey();
            }
        }
        return null;
    }

    public boolean checkIfExistAsDictionaryName(String name) {
        List<DictionaryNames> values = Arrays.asList(DictionaryNames.values());
        for (DictionaryNames value : values) {
            if (value.toString().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
