package com.wttch.wcbs.data.jdbc.config;

import com.wttch.wcbs.core.exception.FrameworkException;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 多数据连接源配置
 *
 * @author wttch
 */
@NoArgsConstructor
@Setter
@Getter
@ConfigurationProperties(prefix = MultipleDataSourceProperties.PREFIX)
public class MultipleDataSourceProperties {
  public static final String PREFIX = "wcbs.data.database";

  /** 主数据源的名字 */
  private String primary;

  @NestedConfigurationProperty private DataSourceProperties db0;
  @NestedConfigurationProperty private DataSourceProperties db1;
  @NestedConfigurationProperty private DataSourceProperties db2;
  @NestedConfigurationProperty private DataSourceProperties db3;
  @NestedConfigurationProperty private DataSourceProperties db4;
  @NestedConfigurationProperty private DataSourceProperties db5;
  @NestedConfigurationProperty private DataSourceProperties db6;
  @NestedConfigurationProperty private DataSourceProperties db7;
  @NestedConfigurationProperty private DataSourceProperties db8;
  @NestedConfigurationProperty private DataSourceProperties db9;

  /**
   * 获取所有的数据源的配置，并使用属性name作为主键
   *
   * @return 所有声明的数据源组成的map`
   */
  public Map<String, DataSourceProperties> dataSourceMap() {
    var fields =
        Arrays.stream(this.getClass().getDeclaredFields())
            .filter(field -> Objects.equals(DataSourceProperties.class, field.getType()))
            .collect(Collectors.toList());
    var map = new HashMap<String, DataSourceProperties>(1);
    for (var field : fields) {
      try {
        var value = (DataSourceProperties) field.get(this);
        if (Objects.nonNull(value)) {
          if (Objects.isNull(value.getName())) {
            throw new FrameworkException(
                String.format("多数据源配置时必须设置name属性, [%s]没有name属性.", field.getName()));
          }
          if (map.containsKey(value.getName())) {
            throw new FrameworkException(
                String.format("数据源名称重复, 数据库[%s]的名字[%s]已存在.", field.getName(), value.getName()));
          }
          map.put(value.getName(), value);
        }
      } catch (IllegalAccessException ignored) {
      }
    }
    return map;
  }
}
