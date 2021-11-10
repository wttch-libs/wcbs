package com.wttch.wcbs.mybatis.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AssignDataSource {
  String value();
}
