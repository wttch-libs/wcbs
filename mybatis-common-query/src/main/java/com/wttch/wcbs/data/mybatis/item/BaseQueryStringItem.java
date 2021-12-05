package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import java.util.List;
import org.apache.ibatis.mapping.BoundSql;

/**
 * 所有简单字符串查询的基类，比如字符串精确匹配，字符串后缀模糊，字符串全模糊等都可以继承该类。
 *
 * <p>该类主要简化了一些父类的操作
 *
 * @author wttch
 */
public abstract class BaseQueryStringItem extends QueryItem<String> {
  /**
   * 获取通用查询的字段的数值类型
   *
   * @return 通用查询的字段的数值类型
   */
  @Override
  public Class<String> valueType() {
    return String.class;
  }

  @Override
  public void setValue(Object value) {
    super.setValue(value);
    afterSetValue();
  }

  /** 当设置值之后对值进行处理 */
  public abstract void afterSetValue();

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
