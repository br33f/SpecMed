package com.i4m1s1.specmed.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Target(ElementType.METHOD) //todo można też dodać TYPE jak ktoś bardzo chce i potem bawić się w serwisy ale brak czasu
@Retention(RetentionPolicy.RUNTIME)
public @interface PermitWith {
    String[] value();
}
