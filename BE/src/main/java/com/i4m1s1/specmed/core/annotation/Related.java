package com.i4m1s1.specmed.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ta adnotacja ma tylko wskazywac podczas programowania że Obiekt danej klasy posiada swoją kolekcje w DB
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Related {
}
