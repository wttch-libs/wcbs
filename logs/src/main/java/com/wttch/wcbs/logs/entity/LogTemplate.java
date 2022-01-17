package com.wttch.wcbs.logs.entity;

import com.wttch.wcbs.logs.annotations.Log;

/**
 * 日志模板的基类。
 *
 * <p>利用 {@link #getKey()} 作为主键，从注解 {@link Log#value()} 来获取对应的日志。
 *
 * <p>日志模版是一个尚未处理的日志，需要根据日志的具体返回信息来将其格式化。返回值的信息一般包含在 {@link Logable}
 * 及其子类中，混入一些数据和具体切面的执行结果。
 *
 * @author wttch
 */
public interface LogTemplate {
  /**
   * 获取模版主键
   *
   * @return 模版主键
   */
  String getKey();
}
