package com.wttch.wcbs.logs;

/**
 * 日志监听，用于日志的格式化和具体日志的消费
 *
 * <p>一般都是 {@link com.wttch.wcbs.logs.Logable} 和 {@link com.wttch.wcbs.logs.LogTemplate} 两者成对的配合使用。
 *
 * @param <T> Logable 的子接口
 * @param <R> LogTemplate 的子接口
 * @author wttch
 */
public interface LogListener<T extends Logable, R extends LogTemplate> {
  /**
   * logable 的具体类型，保存关系使用
   *
   * @return Logable 的子类型
   */
  Class<T> logEntityClass();

  /**
   * LogTemplate 的具体类型, 保存关系使用
   *
   * @return LogTemplate 的子类型
   */
  Class<R> logTemplateClass();

  /**
   * 处理和消费日志
   *
   * @param logInfo 日志的具体信息，包含具体的执行方法，时间，日志模板和参数等
   */
  void consume(LogInfo<Logable, LogTemplate> logInfo);
}
