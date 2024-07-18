package com.yasyl.sailtotm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 限定注解使用在方法参数和字段上
@Retention(RetentionPolicy.RUNTIME) // 使注解在运行时可见
public @interface AdminLicence {
}
