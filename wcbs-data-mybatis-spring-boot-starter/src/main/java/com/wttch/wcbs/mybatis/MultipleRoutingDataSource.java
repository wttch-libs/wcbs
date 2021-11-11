package com.wttch.wcbs.mybatis;

import com.wttch.wcbs.core.exception.FrameworkException;
import com.wttch.wcbs.jdbc.MultipleDataSourceProvider;
import com.wttch.wcbs.jdbc.util.DataSourceContextHelper;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class MultipleRoutingDataSource extends AbstractRoutingDataSource {

  @Getter private Map<String, DataSource> dataSourceMap;
  @Setter private MultipleDataSourceProvider provider;
  @Setter private String primary;

  @Autowired private ConfigurableApplicationContext context;

  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceContextHelper.getDataSourceName();
  }

  @Override
  protected @NotNull DataSource determineTargetDataSource() {
    String lookupKey = (String) this.determineCurrentLookupKey();
    if (this.dataSourceMap.containsKey(lookupKey)) {
      log.debug("从 [{}] 单数据源中返回数据源", lookupKey);
      return this.dataSourceMap.get(lookupKey);
    } else {
      log.debug("从默认数据源中返回数据 [{}]", this.primary);
      return this.dataSourceMap.get(this.primary);
    }
  }

  @Override
  public void afterPropertiesSet() {
    this.dataSourceMap = this.provider.loadDataSources();
    this.dataSourceMap.forEach(
        (name, dataSource) -> {
          String beanName = "DataSource-" + name;
          this.context.getBeanFactory().registerSingleton(beanName, dataSource);
          log.debug("注册[{}]数据源:[{}]", dataSource.getClass().getSimpleName(), beanName);
        });
    log.debug("共加载 [{}] 个数据源", this.dataSourceMap.size());
    if (this.dataSourceMap.containsKey(this.primary)) {
      log.debug("当前的默认数据源是单数据源, 数据源名为 [{}]", this.primary);
    } else {
      throw new FrameworkException("请检查 primary 默认数据库设置, 当前未找到 [" + this.primary + "] 数据源");
    }
  }
}
