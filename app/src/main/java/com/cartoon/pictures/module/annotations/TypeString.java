package com.cartoon.pictures.module.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2016/11/13.
 * Retention:保留的范围
 * 默认值为CLASS. 可选值有三种
 *SOURCE, 只在源码中可用
 *CLASS, 在源码和字节码中可用
 *RUNTIME, 在源码,字节码,运行时均可用
 *
 * @Target
 *可以用来修饰哪些程序元素，如 TYPE, METHOD, CONSTRUCTOR, FIELD, PARAMETER等，未标注则表示可修饰所有
 *
 */

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface TypeString {
}
