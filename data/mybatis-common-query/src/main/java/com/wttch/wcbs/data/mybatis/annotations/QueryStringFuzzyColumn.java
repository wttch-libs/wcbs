package com.wttch.wcbs.data.mybatis.annotations;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;

/**
 * 字段模糊查询
 *
 * @author wttch
 * @see QueryColumn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@QueryColumn(type = QueryParamType.STRING_FUZZY)
public @interface QueryStringFuzzyColumn {
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
