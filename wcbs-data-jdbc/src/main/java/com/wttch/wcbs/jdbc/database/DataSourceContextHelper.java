package com.wttch.wcbs.jdbc.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

/**
 * 数据源切换帮助类
 *
 * <p>将数据源名称保存到 ThreadLocal 中，根据需要进行 DataSource 切换.
 *
 * @author wttch
 */
@Slf4j
public class DataSourceContextHelper {
  private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

  public static void switchTo(String name) {
    log.debug("切换数据源到: {}.", name);
    CONTEXT.set(name);
  }

  public static void restore() {
    CONTEXT.remove();
  }

  @Nullable
  public static String getDataSourceName() {
    return CONTEXT.get();
  }
}
