package com.wttch.wcbs.jdbc.aspect;

import com.wttch.wcbs.jdbc.annotations.AssignDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * 切换数据源的类注解切面.
 *
 * <p>所有的类上的注解在切换数据源时都要比{@link AssignDataSourceMethodAspect} 后执行，可以覆盖方法的数据源切换.
 *
 * @author wttch
 */
@Slf4j
@Aspect
@Order(-6)
public class AssignDataSourceClassAspect {
  @Before("@within(targetDataSource)")
  public void changeDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    AssignDataSourceHelper.changeDataSource(point, targetDataSource);
  }

  @After("@within(targetDataSource)")
  public void restoreDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    AssignDataSourceHelper.restoreDataSource(point, targetDataSource);
  }
}
