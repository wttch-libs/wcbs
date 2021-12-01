package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * 字符串精确查询字段
 *
 * <p>sql: key = 'value'
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryStringItem extends QueryItem<String> {

  @Override
  public Class<String> valueType() {
    return String.class;
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
    return String.format(" %s = ? ", key);
  }

  @Override
  public List<Parameter> parameters() {
    return List.of(new Parameter(String.class, value));
  }
}
