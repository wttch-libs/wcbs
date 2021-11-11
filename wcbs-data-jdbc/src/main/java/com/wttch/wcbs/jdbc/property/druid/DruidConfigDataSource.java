package com.wttch.wcbs.jdbc.property.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;

public class DruidConfigDataSource extends DruidDataSource {

  public void addFilter(Filter filter) {
    this.filters.add(filter);
  }
}