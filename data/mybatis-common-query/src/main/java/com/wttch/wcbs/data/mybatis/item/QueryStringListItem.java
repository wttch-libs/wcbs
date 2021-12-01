package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.util.LinkedList;
import java.util.List;

public class QueryStringListItem extends QueryListItem<String> {
  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.STRING_LIST;
  }

  @Override
  public String queryExpression() {
    if (value.isEmpty()) {
      return "";
    }
    var sql = new StringBuilder(String.format(" %s in (", key));
    for (int i = 0; i < value.size(); ++i) {
      sql.append("?");
      if (i != value.size() - 1) {
        sql.append(",");
      }
    }
    sql.append(")");
    return sql.toString();
  }

  @Override
  public List<Parameter> parameters() {
    var params = new LinkedList<Parameter>();
    for (var v : value) {
      params.add(new Parameter(rawType(), v));
    }
    return params;
  }

  @Override
  public Class<String> rawType() {
    return String.class;
  }
}
