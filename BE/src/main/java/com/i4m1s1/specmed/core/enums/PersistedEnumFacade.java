package com.i4m1s1.specmed.core.enums;

import com.i4m1s1.specmed.core.enums.persistence.DictionarySM;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Klasa dostepu do enumow z bazy
 * jest zrobiona zeby ludzie korzystali z tych metod przy pisaniu,
 * a szczegoly implementacyjne nizej(stąd do persist) będą najwyzej zmieniane.
 *
 * Pozwoli to nam zrownoleglic prace
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

@Component
public class PersistedEnumFacade {

    @Autowired
    DictionaryRepository repository;

    public Map<Integer, String> getDictByNameMap(DictionaryNames name) {
        DictionarySM dict = repository.findByDictionaryName(name);
        return dict.getDictMap();
    }

    public List<String> getDictByNameList(DictionaryNames name) {
        Map<Integer, String> map = getDictByNameMap(name);
        return new ArrayList<>(map.values());
    }
}
