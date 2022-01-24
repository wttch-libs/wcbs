package com.wttch.wcbs.logs;

import java.util.Map;

/**
 * 日志模板管理器
 *
 * @author wttch
 */
public interface LogTemplateManager {
  /**
   * 加载所有的日志模板
   *
   * @return 所有的日志模板
   */
  Map<String, LogTemplate> load();

  /**
   * 获取日志模板，获取 {@link LogTemplate#getKey()} 为 key 的日志模板
   *
   * @param key 日志模板的 key
   * @return 指定key对应的日志模板
   */
  LogTemplate getLogTemplate(String key);
}
