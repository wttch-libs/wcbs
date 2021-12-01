package com.wttch.wcbs.data.jdbc.database.provider;

import java.util.List;
import java.util.Map;

/**
 * 初始化sql路径提供器.
 *
 * @author wttch
 */
public interface InitSqlPathProvider {
  /**
   * 加载多数据源的不同的初始化sql的路径位置
   *
   * @return 所有的数据源所有关联的初始化sql路径位置
   */
  Map<String, List<String>> loadInitSqlPath();
}
