package com.wttch.wcbs.jdbc.aspect;

import com.wttch.wcbs.jdbc.annotations.AssignDataSource;
import com.wttch.wcbs.jdbc.database.DataSourceContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

/**
 * 数据库源切换帮助类. 在多种的切面地方统一使用该方法进行数据库源切换.
 *
 * @author wttch
 */
@Slf4j
public class AssignDataSourceHelper {
  private AssignDataSourceHelper() {}

  public static void changeDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    String dataSourceName = targetDataSource.value();
    DataSourceContextHelper.switchTo(dataSourceName);
    log.debug("线程[{}]切换到数据源: [{}]", Thread.currentThread().getName(), dataSourceName);
  }

  public static void restoreDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    DataSourceContextHelper.restore();
    log.debug(
        "线程[{}]恢复默认数据源: [{}] > [{}]",
        Thread.currentThread().getName(),
        targetDataSource.value(),
        point.getSignature());
  }
}
