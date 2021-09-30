package com.wttch.wcbs.autoconfigure;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@EnableConfigurationProperties({WcbsProperties.class})
public class WcbsAutoConfiguration {
  private final WcbsProperties wcbsProperties;
  private final ApplicationContext ctx;

  public WcbsAutoConfiguration(WcbsProperties wcbsProperties, ApplicationContext ctx) {
    this.wcbsProperties = wcbsProperties;
    this.ctx = ctx;
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
    final var initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(databasePopulator());
    return initializer;
  }

  private DatabasePopulator databasePopulator() {
    final var populator = new ResourceDatabasePopulator();
    Resource[] resources;
    try {
      resources = ctx.getResources("classpath:init/mysql/function.sql");
      resources =
          Arrays.stream(resources)
              .filter(resource -> resource.getFilename() != null)
              .sorted(Comparator.comparing(Resource::getFilename))
              .collect(Collectors.toList())
              .toArray(new Resource[] {});
      for (var resource : resources) {
        populator.addScript(resource);
        populator.setSeparator(";;");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return populator;
  }
}
