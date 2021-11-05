package com.wttch.wcbs.autoconfigure;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

/**
 * spring context 帮助类，项目启动时自动注入静态的 ApplicationContext 到该类中，方便后续直接调用 ApplicationContext。
 *
 * @author wttch
 */
public class SpringContextHelper implements ApplicationContextAware {
  @Nullable private static ApplicationContext context;

  @Override
  public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
    setContext(context);
  }

  private static synchronized void setContext(@NotNull ApplicationContext context) {
    SpringContextHelper.context = context;
  }

  @Nullable
  public static synchronized ApplicationContext getContext() {
    return context;
  }

  @Nullable
  public static <T> T getBean(Class<T> beanClass) {
    return Optional.ofNullable(getContext()).map(ctx -> ctx.getBean(beanClass)).orElse(null);
  }

  @Nullable
  public static Object getBean(String name) {
    return Optional.ofNullable(getContext()).map(ctx -> ctx.getBean(name)).orElse(null);
  }

  @Nullable
  public static <T> T getBean(String name, Class<T> beanClass) {
    return Optional.ofNullable(getContext()).map(ctx -> ctx.getBean(name, beanClass)).orElse(null);
  }
}
