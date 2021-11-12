package com.wttch.wcbs.jdbc.util;

import com.wttch.wcbs.core.exception.FrameworkException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 数据源路由，可以实现多个数据源的切换。
 *
 * <p>抽象DataSource实现，它根据查找键将getConnection()调用路由到各种目标数据源之一。 后者通常（但不一定）通过一些线程绑定的事务上下文来确定。
 *
 * @author wttch
 */
@Slf4j
public class MultipleRoutingDataSource extends AbstractRoutingDataSource {
  /** 数据源Map */
  @Getter private Map<String, DataSource> dataSourceMap;
  /** 多数据的提供者 */
  @Setter private MultipleDataSourceProvider provider;
  /** 主数据源 key */
  @Setter private String primary;

  @Setter(onMethod_ = {@Autowired})
  private ConfigurableApplicationContext applicationContext;

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
          applicationContext.getBeanFactory().registerSingleton(beanName, beanName);
          log.debug("加载[{}]数据源:[{}]", dataSource.getClass().getSimpleName(), beanName);
        });
    log.debug("共加载 [{}] 个数据源", this.dataSourceMap.size());
    if (this.dataSourceMap.containsKey(this.primary)) {
      log.debug("当前的默认数据源是 [{}]", this.primary);
    } else {
      throw new FrameworkException("请检查 primary 默认数据库设置, 当前未找到 [" + this.primary + "] 数据源");
    }
  }
}
