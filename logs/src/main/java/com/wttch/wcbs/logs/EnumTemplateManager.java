package com.wttch.wcbs.logs;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 枚举类型的日志模板的日志模板管理器
 *
 * <p>该管理器的日志模板必须实现 {@link LogTemplate} 并且是枚举类型，否则该管理器将无法正常工作。
 *
 * @author wttch
 */
public class EnumTemplateManager extends AbstractLogTemplateManager {
  /** 枚举类型的日志模板的具体类型 */
  private final Class<? extends LogTemplate> clazz;

  public EnumTemplateManager(Class<? extends LogTemplate> clazz) {
    this.clazz = clazz;
    loadData();
  }

  /**
   * 加载所有的日志模板
   *
   * @return 所有的日志模板
   */
  @Override
  public Map<String, LogTemplate> load() {
    return Arrays.stream(clazz.getEnumConstants())
        .collect(Collectors.toMap(LogTemplate::getKey, Function.identity()));
  }
}
