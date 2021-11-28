package com.wttch.wcbs.data.mybatis;

import com.wttch.wcbs.data.mybatis.item.QueryItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 实际查询使用的传参。
 *
 * <p>使用 {@link com.wttch.wcbs.data.mybatis.annotations.QueryEntity}
 * 注解标注的对象可以被mybatis插件过滤并生成通用查询sql。
 *
 * @author wttch
 * @see com.wttch.wcbs.data.mybatis.item.QueryItems#getQueryItems(Object)
 */
@AllArgsConstructor
@Getter
public class QueryParams {
  /** 所有通用查询的字段 */
  private List<? extends QueryItem<?>> items;
}
