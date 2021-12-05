package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;

/**
 * 字符串集合查询，查询字段是否在给定的字符串集合中
 *
 * @author wttch
 */
public class QueryStringListItem extends BaseQueryListItem<String> {
  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.STRING_LIST;
  }

  /**
   * 获取实际集合元素内元素的类型。
   *
   * <p>对于给定的集合元素，会判断所有的元素是否是该类的子类，否则将报错；无法进行转换。
   *
   * @return 实际集合元素内元素的类型
   */
  @Override
  public Class<String> rawType() {
    return String.class;
  }
}
