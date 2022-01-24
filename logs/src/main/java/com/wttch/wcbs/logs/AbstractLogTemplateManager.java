package com.wttch.wcbs.logs;

import java.util.Map;

/**
 * 抽象的日志模板管理器， 添加一些通用的实现，主要是用的模板函数的形式，来处理具体的日志管理模板实现。
 *
 * @author wttch
 */
public abstract class AbstractLogTemplateManager implements LogTemplateManager {
  /** 日志模板的保存 map */
  private Map<String, LogTemplate> logTemplateMap;

  /** 加载日志模板并保存在 map 中 */
  protected void loadData() {
    logTemplateMap = load();
  }
  /**
   * 获取日志模板，获取 {@link LogTemplate#getKey()} 为 key 的日志模板
   *
   * @param key 日志模板的 key
   * @return 指定key对应的日志模板
   */
  @Override
  public LogTemplate getLogTemplate(String key) {
    return logTemplateMap.get(key);
  }
}
