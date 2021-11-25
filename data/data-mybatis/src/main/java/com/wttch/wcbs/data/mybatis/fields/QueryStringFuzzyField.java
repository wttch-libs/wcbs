package com.wttch.wcbs.data.mybatis.fields;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;

/**
 * 字符串模糊查询，后缀模糊形式，可以使用索引
 *
 * <p>sql: key like 'value%'
 *
 * @author wttch
 */
public class QueryStringFuzzyField extends QueryField<String> {
  public QueryStringFuzzyField(String value) {
    super(value);
  }

  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.STRING_FUZZY;
  }

  @Override
  public String queryExpression() {
    return String.format(" %s like '%s%%'", key, value);
  }
}
