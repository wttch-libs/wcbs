package com.wttch.wcbs.jdbc.util;

import java.util.Map;
import javax.sql.DataSource;

/**
 * 多数据源提供接口
 *
 * @author wttch
 */
public interface MultipleDataSourceProvider {
  /**
   * 加载所有的数据源
   *
   * @return 数据源map
   */
  Map<String, DataSource> loadDataSources();
}
