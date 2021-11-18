package com.wttch.wcbs.mybatis.autoconfigure;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * mybatis autoconfigure 的配置. 属性完全一样, 不过配置的前缀修改为 "wcbs.data.mybatis"
 *
 * @author wttch
 */
@ConfigurationProperties(prefix = WcbsMybatisProperties.PREFIX)
@NoArgsConstructor
@Getter
@Setter
public class WcbsMybatisProperties {

  public static final String PREFIX = "wcbs.data.mybatis";

  private static final ResourcePatternResolver RESOURCE_RESOLVER =
      new PathMatchingResourcePatternResolver();

  /** mybatis xml 配置文件位置 */
  private String configLocation;

  /** Mybatis mapper 文件位置 */
  private String[] mapperLocations;

  /** 用于搜索类型别名的包. (包分隔符为 ",; \t\n") */
  private String typeAliasesPackage;

  /** 过滤类型别名的超类. 如果没有指定, MyBatis 会将所有从 typeAliasesPackage 中搜索到的类作为类型别名处理 */
  private Class<?> typeAliasesSuperType;

  /** 用于搜索类型处理程序的包. (包分隔符为 ",; \t\n") */
  private String typeHandlersPackage;

  /** 指示是否对MyBatis xml配置文件进行存在检查l */
  private boolean checkConfigLocation = false;

  /** 执行模式 {@link org.mybatis.spring.SqlSessionTemplate} */
  private ExecutorType executorType;

  /** 默认脚本语言驱动程序类. (与mybatis-spring 2.0.2+一起使用时可用) */
  private Class<? extends LanguageDriver> defaultScriptingLanguageDriver;

  /** MyBatis 配置的外化属性 */
  private Properties configurationProperties;

  /** 用于自定义默认设置的 Configuration 对象。如果指定了 {@link #configLocation}，则不使用此属性 */
  @NestedConfigurationProperty private Configuration configuration;

  /**
   * 将mapper所有路径转换为 Resource 数组
   *
   * @return 所有mapper文件路径位置
   */
  public Resource[] resolveMapperLocations() {
    return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0]))
        .flatMap(location -> Stream.of(getResources(location)))
        .toArray(Resource[]::new);
  }

  private Resource[] getResources(String location) {
    try {
      return RESOURCE_RESOLVER.getResources(location);
    } catch (IOException e) {
      return new Resource[0];
    }
  }
}
