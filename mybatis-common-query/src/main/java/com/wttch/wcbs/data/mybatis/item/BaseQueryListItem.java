package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.exception.MybatisException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.logging.log4j.util.Strings;

/**
 * 集合的通用查询，将查询的元素转换成集合进行查询
 *
 * @author wttch
 * @param <T> 实际集合内元素的类型
 */
@SuppressWarnings("rawtypes")
public abstract class BaseQueryListItem<T> extends QueryItem<Collection> {
  /**
   * 获取通用查询的字段的数值类型
   *
   * @return 通用查询的字段的数值类型
   */
  @Override
  public Class<Collection> valueType() {
    return Collection.class;
  }

  /**
   * 获取实际集合元素内元素的类型。
   *
   * <p>对于给定的集合元素，会判断所有的元素是否是该类的子类，否则将报错；无法进行转换。
   *
   * @return 实际集合元素内元素的类型
   */
  public abstract Class<T> rawType();

  /**
   * 获取查询用的sql表达式
   *
   * @return 查询用的sql表达式
   */
  @Override
  public String queryExpression() {
    if (value.isEmpty()) {
      return Strings.EMPTY;
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

  /**
   * 获取参数列表, 为了防止sql注入，将参数类型和值封装起来交给 {@link BoundSql} 来处理生成sql。
   *
   * @return 参数列表
   */
  @Override
  public List<Parameter> parameters() {
    var params = new LinkedList<Parameter>();
    for (var v : value) {
      params.add(new Parameter(rawType(), v));
    }
    return params;
  }

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
