package com.wttch.wcbs.mybatis.property;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 多数据连接源配置
 *
 * @author wttch
 */
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "wttch.data.database")
public class MultipleDataSourceProperty {
  private String primary;
  private Map<String, DataSourceProperty> connection = new HashMap<>();
}
