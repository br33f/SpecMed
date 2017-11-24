package com.i4m1s1.specmed.core.enums.persistence;

import com.i4m1s1.specmed.core.enums.DictionaryNames;
import org.springframework.data.annotation.Id;

import java.util.Map;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class DictionarySM {

    @Id
    private String id;
    private DictionaryNames dictionaryName;
    private Map<Integer, String> dictMap;

    DictionarySM(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DictionaryNames getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(DictionaryNames dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public Map<Integer, String> getDictMap() {
        return dictMap;
    }

    public void setDictMap(Map<Integer, String> dictMap) {
        this.dictMap = dictMap;
    }
}
