package com.wttch.wcbs.mybatis;

import javax.sql.DataSource;
import java.util.Map;

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
