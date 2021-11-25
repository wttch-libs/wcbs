package com.wttch.wcbs.core.util;

import com.wttch.wcbs.core.exception.FrameworkException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring context 帮助类，项目启动时自动注入静态的 ApplicationContext 到该类中，方便后续直接调用 ApplicationContext。
 *
 * <p>此类只能在业务层进行使用，无法在 Bean 加载过程中使用，因为无法确定bean的加载顺序。
 *
 * @author wttch
 */
@Slf4j
public class SpringContextHelper {
  @Nullable private static ConfigurableApplicationContext context;

  private SpringContextHelper() {}

  public static synchronized void setContext(@NotNull ConfigurableApplicationContext context) {
    SpringContextHelper.context = context;
    log.debug("已为 SpringContextHelper 注入 SpringApplicationContext.");
  }

  @NotNull
  public static synchronized ConfigurableApplicationContext getContext() {
    return Optional.ofNullable(context)
        .orElseThrow(() -> new FrameworkException("无法获取SpringApplicationContext!"));
  }

  public static <T> @NotNull T getBean(Class<T> beanClass) {
    return getContext().getBean(beanClass);
  }

  public static @NotNull Object getBean(String name) {
    return getContext().getBean(name);
  }

  public static <T> @NotNull T getBean(String name, Class<T> beanClass) {
    return getContext().getBean(name, beanClass);
  }

  public static void initializeBean(Object bean, String beanName) {
    getContext().getBeanFactory().initializeBean(bean, beanName);
  }

  public static void registerSingleton(String beanName, Object bean) {
    getContext().getBeanFactory().registerSingleton(beanName, bean);
  }
}
