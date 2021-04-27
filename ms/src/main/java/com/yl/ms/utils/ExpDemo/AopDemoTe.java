package com.yl.ms.utils.ExpDemo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//注解的保留期限
@Target(ElementType.METHOD)//可以使用注解的类型
@Documented
public @interface AopDemoTe {

}
