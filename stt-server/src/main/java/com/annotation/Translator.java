package com.annotation;

import com.enumeration.TranType;
import com.enumeration.TranslatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Translator {
    TranType type();

    TranslatorType tranType();

    String[] inputParams() default {""};

    String[] outputParams() default {""};
}
