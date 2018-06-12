package com.travtusworks.adam.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by teodora on 12.06.2018.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AdamScope {
}
