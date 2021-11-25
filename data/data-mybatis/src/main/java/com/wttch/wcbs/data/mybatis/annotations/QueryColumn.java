package com.wttch.wcbs.data.mybatis.annotations;

import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;

/**
 * api接口查询时，通用的查询方式声明
 *
 * <p>声明查询字段的表名和字段名
 *
 * @author wttch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface QueryColumn {
  /**
   * 获取查询的表名
   *
   * @return 查询的表名
   */
  String tableName() default "";

  /**
   * 获取查询的字段名
   *
   * @return 查询的字段名
   * @see #columnName()
   */
  @AliasFor("columnName")
  String value() default "";

  /**
   * 获取查询的字段名
   *
   * @return 查询的字段名
   * @see #value()
   */
  @AliasFor("value")
  String columnName() default "";

  /**
   * 获取查询的表名和字段名的分割符号
   *
   * @return 查询的表名和字段名的分割符号
   */
  String delimiter() default ".";
}
