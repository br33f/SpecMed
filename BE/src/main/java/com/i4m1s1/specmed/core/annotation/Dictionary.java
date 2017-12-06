package com.i4m1s1.specmed.core.annotation;

import com.i4m1s1.specmed.core.dict.DictionaryNames;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adnotacja pokazująca jedynie że zawartość danego pola powinna mieć odzwierciedlenie w slowniku
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dictionary {
    DictionaryNames value();
}
