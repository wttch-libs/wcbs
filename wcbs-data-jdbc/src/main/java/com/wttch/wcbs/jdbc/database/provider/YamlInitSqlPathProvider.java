package com.wttch.wcbs.jdbc.database.provider;

import com.wttch.wcbs.core.util.MapUtils;
import com.wttch.wcbs.jdbc.config.DataSourceProperty;
import com.wttch.wcbs.jdbc.config.MultipleDataSourceProperty;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * yaml 数据库初始化sql路径提供器
 *
 * @author wttch
 */
public class YamlInitSqlPathProvider implements InitSqlPathProvider {
  private final MultipleDataSourceProperty multipleDataSourceProperty;

  /** 数据源 driverClassName --> initFunctionSqlPath */
  private final Map<String, String> driverClassName2FunctionPathMap =
      Map.of("com.mysql.cj.jdbc.Driver", "classpath:init/function/mysql.sql");

  public YamlInitSqlPathProvider(MultipleDataSourceProperty multipleDataSourceProperty) {
    this.multipleDataSourceProperty = multipleDataSourceProperty;
  }

  /**
   * 加载多数据源的不同的初始化sql的路径位置
   *
   * @return 所有的数据源所有关联的初始化sql路径位置
   */
  @Override
  public Map<String, List<String>> loadInitSqlPath() {
    var initSqlPathMap =
        MapUtils.mapTo(
            multipleDataSourceProperty.dataSourceMap(), DataSourceProperty::getInitSqlPath);
    var sqlFunctionLoadPredicateMap =
        MapUtils.mapTo(
            multipleDataSourceProperty.dataSourceMap(), DataSourceProperty::getLoadFunction);
    var driverMap =
        MapUtils.mapTo(
            multipleDataSourceProperty.dataSourceMap(), DataSourceProperty::getDriverClassName);
    for (var key : initSqlPathMap.keySet()) {
      if (sqlFunctionLoadPredicateMap.getOrDefault(key, false)) {
        var driver = driverMap.get(key);
        if (Objects.nonNull(driver)) {
          initSqlPathMap.get(key).add(0, driverClassName2FunctionPathMap.get(driver));
        }
      }
    }
    return initSqlPathMap;
  }
}
