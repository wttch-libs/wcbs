package com.wttch.wcbs.data.jdbc.durid;

import com.wttch.wcbs.data.jdbc.durid.filter.DruidFilter;
import com.wttch.wcbs.data.jdbc.durid.filter.DruidWebStatFilter;
import java.util.Properties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * druid 数据库连接池相关的配置
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidDataSourceProperties {
  /** 初始连接数 */
  private Integer initialSize = 5;
  /** 最小连接池数量 */
  private Integer minIdle = 10;
  /** 最大连接池数量 */
  private Integer maxActive = 40;
  /** 配置获取连接等待超时的时间 */
  private Integer maxWait = 60000;
  /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
  private Integer timeBetweenEvictionRunsMillis = 60000;
  /** 配置一个连接在池中最小生存的时间，单位是毫秒 */
  private Integer minEvictableIdleTimeMillis = 300000;
  /** 配置一个连接在池中最大生存的时间，单位是毫秒 */
  private Integer maxEvictableIdleTimeMillis = 1800000;
  /** 配置检测连接是否有效 */
  private String validationQuery = "SELECT 1 FROM DUAL";

  /** 如果为true（默认true），当应用向连接池申请连接，并且testOnBorrow为false时，连接池将会判断连接是否处于空闲状态，如果是，则验证这条连接是否可用 */
  private Boolean testWhileIdle = true;
  /** 如果为true（默认为false），当应用向连接池申请连接时，连接池会判断这条连接是否是可用的。 */
  private Boolean testOnBorrow = true;
  /** 如果为true（默认false），当应用使用完连接，连接池回收连接的时候会判断该连接是否还可用。 */
  private Boolean testOnReturn = false;

  /** 连接出错后再尝试连接次数 */
  private Integer connectionErrorRetryAttempts = 3;
  /**
   * true表示pool向数据库请求连接失败后标记整个pool为block并close，就算后端数据库恢复正常也不进行重连，客户端对pool的请求都拒绝掉。false表示不会标记
   * pool为block，新的请求都会尝试去数据库请求connection。默认为false。因此，如果想让数据库和网络故障恢复之后，pool能继续请求正常资源必须把此项配置设为false
   */
  private Boolean breakAfterAcquireFailure = false;

  private Integer validationQueryTimeout = -1;
  private Boolean poolPreparedStatements = false;
  private Integer maxOpenPreparedStatements = -1;
  private Boolean sharePreparedStatements = false;
  private Properties connectionProperties;
  private String filters = "stat,wall";
  private String webStatFilterExclusions = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*";
  private Boolean removeAbandoned = false;
  private Long removeAbandonedTimeoutMillis = 300000L;

  @NestedConfigurationProperty private DruidFilter filter = new DruidFilter();

  @NestedConfigurationProperty private DruidWebStatFilter webStatFilter;
  @NestedConfigurationProperty private DruidStatViewServlet statViewServlet;
}
