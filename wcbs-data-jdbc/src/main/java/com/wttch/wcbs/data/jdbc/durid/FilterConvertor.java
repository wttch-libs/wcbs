package com.wttch.wcbs.data.jdbc.durid;

import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.wttch.wcbs.data.jdbc.durid.filter.DruidWallFilter;
import org.springframework.beans.BeanUtils;

/**
 * filter 的转换器，将框架声明的配置，转换为druid的 Filter
 *
 * @author wttch
 */
public class FilterConvertor {
  private FilterConvertor() {}

  public static WallFilter from(DruidWallFilter filter) {
    var wallFilter = new WallFilter();
    var wallConfig = new WallConfig();
    BeanUtils.copyProperties(filter.getConfig(), wallConfig);
    wallFilter.setConfig(wallConfig);
    return wallFilter;
  }
}
