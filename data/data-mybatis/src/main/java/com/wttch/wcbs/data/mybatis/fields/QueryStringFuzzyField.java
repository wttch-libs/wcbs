package com.wttch.wcbs.data.mybatis.fields;

import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;

import java.util.List;

/**
 * 字符串模糊查询，后缀模糊形式，可以使用索引
 *
 * <p>sql: key like 'value%'
 *
 * @author wttch
 */
public class QueryStringFuzzyField extends QueryField<String> {
  public QueryStringFuzzyField() {}

  public QueryStringFuzzyField(String value) {
    setValue(value);
  }

  @Override
  public Class<String> handleType() {
    return String.class;
  }

  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.STRING_FUZZY;
  }

  @Override
  public String queryExpression() {
    return String.format(" %s like ?", key);
  }

  @Override
  public List<Parameter> parameters() {
    return List.of(new Parameter(handleType(), value + "%"));
  }
}
