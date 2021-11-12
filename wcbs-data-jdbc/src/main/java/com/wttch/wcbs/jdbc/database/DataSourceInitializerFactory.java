package com.wttch.wcbs.jdbc.database;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/** @author wttch */
@Slf4j
public class DataSourceInitializerFactory {
  private DataSourceInitializerFactory() {}

  public static DataSourceInitializer createInitializer(
      ApplicationContext ctx,
      String dataSourceName,
      DataSource dataSource,
      List<String> initSqlPath) {
    initSqlPath = Optional.ofNullable(initSqlPath).orElse(List.of());
    var initializer = new DataSourceInitializer();
    var populator = new ResourceDatabasePopulator();
    populator.setSeparator(";;");
    int sum = 0;
    try {
      for (var path : initSqlPath) {
        Resource[] resources = ctx.getResources(path);
        sum += resources.length;
        Arrays.stream(resources)
            .filter(resource -> resource.getFilename() != null)
            .filter(Resource::exists)
            .sorted(Comparator.comparing(Resource::getFilename))
            .collect(Collectors.toList())
            .forEach(populator::addScript);
      }
      initializer.setDataSource(dataSource);
      initializer.setDatabasePopulator(populator);
      log.info("数据源[{}], 已从{}个文件夹，加载{}个初始化sql文件", dataSourceName, initSqlPath.size(), sum);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return initializer;
  }
}
