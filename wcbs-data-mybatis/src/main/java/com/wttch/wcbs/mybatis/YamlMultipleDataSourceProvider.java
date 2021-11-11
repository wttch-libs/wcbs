package com.wttch.wcbs.mybatis;

import com.wttch.wcbs.mybatis.property.MultipleDataSourceProperty;
import com.wttch.wcbs.mybatis.util.DataSourceFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;

/**
 * yml 多数据源配置供应器
 *
 * @author wttch
 */
@AllArgsConstructor
public class YamlMultipleDataSourceProvider implements MultipleDataSourceProvider {
  private final MultipleDataSourceProperty multipleDataSourceProperty;
  /**
   * 加载所有的数据源
   *
   * @return 数据源map
   */
  @Override
  public Map<String, DataSource> loadDataSources() {
    var dataSourcePropertiesMap = multipleDataSourceProperty.dataSourceMap();
    var dataSourceMap = new HashMap<String, DataSource>(dataSourcePropertiesMap.size());
    for (var entry : dataSourcePropertiesMap.entrySet()) {
      dataSourceMap.put(
          entry.getKey(), DataSourceFactory.createDataSource(entry.getKey(), entry.getValue()));
    }
    return dataSourceMap;
  }
}
