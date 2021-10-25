package com.wttch.wcbs.autoconfigure;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Slf4j
@Configuration
@AutoConfigureAfter({DataSourceTransactionManagerAutoConfiguration.class})
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
    populator.setSeparator(";;");
    try {
      var functionResource = ctx.getResource("classpath:init/mysql/function.sql");
      populator.addScript(functionResource);
      log.info("已加载mysql初始化函数");

      var paths = Optional.ofNullable(wcbsProperties.getInitSqlPath()).orElse(new String[] {});
      var sum = 0;
      for (var path : paths) {
        var resources = ctx.getResources(path);
        sum += resources.length;
        Arrays.stream(resources)
            .filter(resource -> resource.getFilename() != null)
            .filter(Resource::exists)
            .sorted(Comparator.comparing(Resource::getFilename))
            .collect(Collectors.toList())
            .forEach(populator::addScript);
      }
      log.info("已从{}个文件夹，加载{}个初始化sql文件", paths.length, sum);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return populator;
  }
}
