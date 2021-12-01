package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.exception.MybatisException;
import java.util.LinkedList;
import java.util.List;

public abstract class QueryListItem<T> extends QueryItem<List> {
  @Override
  public Class<List> valueType() {
    return List.class;
  }

  public abstract Class<T> rawType();

  @Override
  public void setValue(Object value) {
    if (valueType().isInstance(value)) {
      var newValue = new LinkedList<T>();
      for (var v : valueType().cast(value)) {
        newValue.add(rawType().cast(v));
      }
      this.value = newValue;
    } else {
      throw new MybatisException(String.format("无法将 %s 转换为 %s", value, this.getClass()));
    }
  }
}
