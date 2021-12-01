package com.wttch.wcbs.data.mybatis.annotations;

import java.lang.annotation.*;

/**
 * api接口查询时，使用该接口标注的类会被框架扫描进行处理
 *
 * <p>声明查询字段的表名和字段名
 *
 * @author wttch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface QueryEntity {}
