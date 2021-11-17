package com.wttch.wcbs.data.jdbc.durid.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 使用WallFilter能让Select/Delete/Update的SQL自动加上租户隔离字段
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidWallFilter {
  @NestedConfigurationProperty private DruidWallConfig config = new DruidWallConfig();
}
