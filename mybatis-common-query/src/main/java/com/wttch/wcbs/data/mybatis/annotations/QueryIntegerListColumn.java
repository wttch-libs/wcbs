package com.wttch.wcbs.data.mybatis.annotations;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 整型列表查询
 *
 * @author wttch
 * @see QueryColumn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@QueryColumn(type = QueryParamType.INTEGER_LIST)
public @interface QueryIntegerListColumn {
  /**
   * 获取查询的表名
   *
   * @return 查询的表名
   */
  @AliasFor(annotation = QueryColumn.class)
  String tableName() default "";

  /**
   * 获取查询的字段名
   *
   * @return 查询的字段名
   */
  @AliasFor(annotation = QueryColumn.class)
  String columnName();

  /**
   * 获取查询的表名和字段名的分割符号
   *
   * @return 查询的表名和字段名的分割符号
   */
  @AliasFor(annotation = QueryColumn.class)
  String delimiter() default ".";
}
