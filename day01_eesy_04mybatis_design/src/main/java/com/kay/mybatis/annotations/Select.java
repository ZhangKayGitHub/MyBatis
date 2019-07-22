package com.kay.mybatis.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询的注解
 */
//改变一下该注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
//同时还要改变一下他出现的位置
@Target(ElementType.METHOD)
public @interface Select {
    /**
     * 配置sql语句
     * @return
     */
    String value();
}
