package com.wttch.wcbs.mybatis.aspect;

import com.wttch.wcbs.jdbc.annotations.AssignDataSource;
import com.wttch.wcbs.jdbc.util.DataSourceContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
@Order(-6)
public class AssignDataSourceClassAspect {
  @Before("@within(targetDataSource)")
  public void changeDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    String dataSourceName = targetDataSource.value();
    DataSourceContextHelper.switchTo(dataSourceName);
    log.debug("切换数据源: [{}]", dataSourceName);
  }

  @After("@within(targetDataSource)")
  public void restoreDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    DataSourceContextHelper.clear();
    log.debug("回收数据源: [{}] > [{}]", targetDataSource.value(), point.getSignature());
  }
}
