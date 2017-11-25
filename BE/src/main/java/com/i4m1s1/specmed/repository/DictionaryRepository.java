package com.i4m1s1.specmed.repository;

import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.core.dict.persistence.DictionarySM;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface DictionaryRepository  extends MongoRepository<DictionarySM, String>{

    DictionarySM findByDictionaryName(DictionaryNames name);
}
