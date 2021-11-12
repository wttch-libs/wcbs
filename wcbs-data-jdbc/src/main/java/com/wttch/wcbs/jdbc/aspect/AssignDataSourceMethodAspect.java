package com.wttch.wcbs.jdbc.aspect;

import com.wttch.wcbs.jdbc.annotations.AssignDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * 切换数据源的方法注解切面.
 *
 * <p>所有的类上的注解在切换数据源时都要比{@link AssignDataSourceClassAspect} 先执行，会被后者的数据源切换覆盖.
 *
 * @author wttch
 */
@Slf4j
@Aspect
@Order(-5)
public class AssignDataSourceMethodAspect {
  @Before("@annotation(targetDataSource)")
  public void changeDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    AssignDataSourceHelper.changeDataSource(point, targetDataSource);
  }

  @After("@annotation(targetDataSource)")
  public void restoreDataSource(JoinPoint point, AssignDataSource targetDataSource) {
    AssignDataSourceHelper.restoreDataSource(point, targetDataSource);
  }
}
