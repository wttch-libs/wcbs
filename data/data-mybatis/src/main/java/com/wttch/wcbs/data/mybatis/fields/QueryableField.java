package com.wttch.wcbs.data.mybatis.fields;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;

/**
 * 可以查询的字段
 *
 * @author wttch
 */
public interface QueryableField {
  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  QueryParamType queryParamType();

  /**
   * 获取查询用的sql表达式
   *
   * @return 查询用的sql表达式
   */
  String queryExpression();

  /**
   * 设置查询的key
   *
   * @param key 查询的key
   */
  void setKey(String key);
}
