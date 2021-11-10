package com.wttch.wcbs.mybatis.property.druid.filter;

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
  @NestedConfigurationProperty private DruidStatFilter stat;
  @NestedConfigurationProperty private DruidWallFilter wall;
}
