package com.wttch.wcbs.mybatis.autoconfigure;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 可以自动配置添加 MapperLocation
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WcbsMapperLocationsProvider {
  /** 资源路径位置 */
  private List<String> locations;

  public WcbsMapperLocationsProvider(String... locations) {
    this(List.of(locations));
  }

  public static WcbsMapperLocationsProvider of(String... locations) {
    return new WcbsMapperLocationsProvider(locations);
  }
}
