package com.wttch.wcbs.data.mybatis.fields;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;

/**
 * 字符串精确查询字段
 *
 * <p>sql: key = 'value'
 *
 * @author wttch
 */
public class QueryStringField extends QueryField<String> {
  public QueryStringField(String value) {
    super(value);
  }

  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.STRING;
  }

  /**
   * 获取查询用的sql表达式
   *
   * @return 查询用的sql表达式
   */
  @Override
  public String queryExpression() {
    return String.format(" %s = '%s' ", key, value);
  }
}
