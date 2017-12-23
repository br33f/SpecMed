package com.i4m1s1.specmed.facade;

import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.core.dict.persistence.DictionarySM;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class DictionaryOperationFacadeTest {

    private static final List<String> CONTENT = Arrays.asList("POS1", "POS2", "POS3");
    private static final DictionaryNames SAMPLE_NAME = DictionaryNames.SPECIALIZATION;
    private Map<Integer, String> dicts = new HashMap<>();

    @Mock
    private DictionaryRepository repository;
    @InjectMocks
    private DictionaryOperationFacade facade;

    @Before
    public void init() {
        prepareDictMap();
    }

    @Test
    public void shouldListAndMapBeTheSame() throws Exception {
        //given
        DictionarySM dictionarySM = new DictionarySM();
        dictionarySM.setDictMap(dicts);
        dictionarySM.setDictionaryName(SAMPLE_NAME);
        when(repository.findByDictionaryName(SAMPLE_NAME)).thenReturn(dictionarySM);

        //when
        List<String> resultList = facade.getDictByNameList(SAMPLE_NAME);
        Map<Integer, String> resultMap = facade.getDictByNameMap(SAMPLE_NAME);

        //then
        assertEquals(resultList, new ArrayList<>(dicts.values()));
    }

    @Test
    public void shouldGetIdByNameInDictionaryReturnKey() throws Exception {
        //given
        Integer key = 1;
        String toCheck = dicts.get(key);
        DictionarySM dictionarySM = new DictionarySM();
        dictionarySM.setDictMap(dicts);
        dictionarySM.setDictionaryName(SAMPLE_NAME);
        when(repository.findByDictionaryName(SAMPLE_NAME)).thenReturn(dictionarySM);

        //when
        Integer result = facade.getIdByNameInDictionary(SAMPLE_NAME, toCheck);

        //then
        assertEquals(key, result);
    }

    private void prepareDictMap() {
        this.dicts = new HashMap<>();
        for (int i = 0; i < CONTENT.size(); i++) {
            dicts.put(i, CONTENT.get(i));
        }
    }
}