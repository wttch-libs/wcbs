package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import lombok.NoArgsConstructor;

/**
 * 字符串精确查询字段
 *
 * <p>sql: key = 'value'
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryStringItem extends BaseQueryStringItem {

  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
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

  /** 当设置值之后对值进行处理 */
  @Override
  public void afterSetValue() {
    this.value = String.format("%s", value);
  }
}
