package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * 字符串全模糊查询
 *
 * <p>sql: key like '%value%'
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryStringFullFuzzyItem extends QueryItem<String> {

  @Override
  public Class<String> valueType() {
    return String.class;
  }

  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.STRING_FULL_FUZZY;
  }

  @Override
  public String queryExpression() {
    return String.format(" %s like ? ", key);
  }

  @Override
  public List<Parameter> parameters() {
    return List.of(new Parameter(String.class, String.format("%%%s%%", value)));
  }
}
