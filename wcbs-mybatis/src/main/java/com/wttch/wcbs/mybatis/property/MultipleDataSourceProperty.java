package com.wttch.wcbs.mybatis.property;

import java.util.*;
import java.util.stream.Collectors;

import com.wttch.wcbs.core.exception.FrameworkException;
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
@ConfigurationProperties(prefix = "wttch.data.database")
public class MultipleDataSourceProperty {
  /** 主数据源的名字 */
  private String primary;

  @NestedConfigurationProperty private DataSourceProperty db0;
  @NestedConfigurationProperty private DataSourceProperty db1;
  @NestedConfigurationProperty private DataSourceProperty db2;
  @NestedConfigurationProperty private DataSourceProperty db3;
  @NestedConfigurationProperty private DataSourceProperty db4;
  @NestedConfigurationProperty private DataSourceProperty db5;
  @NestedConfigurationProperty private DataSourceProperty db6;
  @NestedConfigurationProperty private DataSourceProperty db7;
  @NestedConfigurationProperty private DataSourceProperty db8;
  @NestedConfigurationProperty private DataSourceProperty db9;

  /**
   * 获取所有的数据源的配置，并使用属性name作为主键
   *
   * @return 所有声明的数据源组成的map`
   */
  public Map<String, DataSourceProperty> dataSourceMap() {
    var fields =
        Arrays.stream(this.getClass().getDeclaredFields())
            .filter(field -> Objects.equals(DataSourceProperty.class, field.getType()))
            .collect(Collectors.toList());
    var map = new HashMap<String, DataSourceProperty>(1);
    for (var field : fields) {
      try {
        var value = (DataSourceProperty) field.get(this);
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
