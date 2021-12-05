package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import lombok.NoArgsConstructor;

/**
 * 整型列表查询
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryIntegerListItem extends BaseQueryListItem<Integer> {
  /**
   * 获取实际集合元素内元素的类型。
   *
   * <p>对于给定的集合元素，会判断所有的元素是否是该类的子类，否则将报错；无法进行转换。
   *
   * @return 实际集合元素内元素的类型
   */
  @Override
  public Class<Integer> rawType() {
    return Integer.class;
  }

  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.INTEGER_LIST;
  }
}
