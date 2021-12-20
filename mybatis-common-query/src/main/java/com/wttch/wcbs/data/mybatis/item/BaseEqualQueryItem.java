package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import java.util.List;
import org.apache.ibatis.mapping.BoundSql;

/**
 * 判读相等的基类
 *
 * @author wttch
 * @param <T> 具体的值类型
 */
public abstract class BaseEqualQueryItem<T> extends QueryItem<T> {

  /**
   * 获取查询用的sql表达式
   *
   * @return 查询用的sql表达式
   */
  @Override
  public String queryExpression() {
    return String.format(" %s = ? ", key);
  }

  /**
   * 获取参数列表, 为了防止sql注入，将参数类型和值封装起来交给 {@link BoundSql} 来处理生成sql。
   *
   * @return 参数列表
   */
  @Override
  public List<Parameter> parameters() {
    return List.of(new Parameter(valueType(), value));
  }
}
