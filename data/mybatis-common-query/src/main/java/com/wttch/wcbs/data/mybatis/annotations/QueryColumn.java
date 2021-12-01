package com.wttch.wcbs.data.mybatis.annotations;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.lang.annotation.*;

/**
 * api接口查询时，通用的查询方式声明
 *
 * <p>声明查询字段的表名和字段名
 *
 * @author wttch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
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
   */
  String columnName() default "";

  /**
   * 获取查询的表名和字段名的分割符号
   *
   * @return 查询的表名和字段名的分割符号
   */
  String delimiter() default ".";

  QueryParamType type() default QueryParamType.STRING;
}
