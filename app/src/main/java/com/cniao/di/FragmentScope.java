package com.cniao.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by chenqi on 2017/6/9.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
