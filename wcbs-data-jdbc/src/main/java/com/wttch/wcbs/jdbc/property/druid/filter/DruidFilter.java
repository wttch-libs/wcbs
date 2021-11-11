package com.wttch.wcbs.jdbc.property.druid.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 过滤器
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidFilter {
  @NestedConfigurationProperty private DruidStatFilter stat = new DruidStatFilter();
  @NestedConfigurationProperty private DruidWallFilter wall = new DruidWallFilter();
}
