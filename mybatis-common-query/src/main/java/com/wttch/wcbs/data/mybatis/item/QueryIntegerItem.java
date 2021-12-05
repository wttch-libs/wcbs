package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import lombok.NoArgsConstructor;

/**
 * 整型相等
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryIntegerItem extends BaseEqualQueryItem<Integer> {
  /**
   * 获取通用查询的字段的数值类型
   *
   * @return 通用查询的字段的数值类型
   */
  @Override
  public Class<Integer> valueType() {
    return Integer.class;
  }

  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.INTEGER;
  }
}
