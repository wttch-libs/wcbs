package com.wttch.wcbs.logs.component;

import com.wttch.wcbs.logs.LogInfo;
import com.wttch.wcbs.logs.LogListener;
import com.wttch.wcbs.logs.LogTemplate;
import com.wttch.wcbs.logs.LogTemplateManager;
import com.wttch.wcbs.logs.Logable;
import com.wttch.wcbs.logs.annotations.Log;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author wttch
 */
@Aspect
@Component
public class LogAspect {
  private final ThreadLocal<Long> execTime = new ThreadLocal<>();

  private final LogTemplateManager logTemplateManager;
  private final ObjectProvider<LogListener<?, ?>> logListenerProvider;
  private final Map<Class<?>, Map<Class<?>, LogListener<? extends Logable, ? extends LogTemplate>>>
      logListenerMap;

  public LogAspect(
      LogTemplateManager logTemplateManager,
      ObjectProvider<LogListener<?, ?>> logListenerProvider) {
    this.logTemplateManager = logTemplateManager;
    this.logListenerProvider = logListenerProvider;

    logListenerMap =
        logListenerProvider.stream()
            .collect(
                Collectors.groupingBy(
                    LogListener::logEntityClass,
                    Collectors.toMap(LogListener::logTemplateClass, Function.identity())));
  }

  private LogListener<? extends Logable, ? extends LogTemplate> getLogListener(
      Class<?> entityClass, Class<?> logTemplateClass) {
    for (var entityEntry : logListenerMap.entrySet()) {
      if (entityEntry.getKey().isAssignableFrom(entityClass)) {
        for (var templateEntry : entityEntry.getValue().entrySet()) {
          if (templateEntry.getKey().isAssignableFrom(logTemplateClass)) {
            return templateEntry.getValue();
          }
        }
      }
    }
    return null;
  }

  @Pointcut("@annotation(com.wttch.wcbs.logs.annotations.Log)")
  public void logPointcut() {}

  @Before("@annotation(com.wttch.wcbs.logs.annotations.Log)")
  public void beforeLogPointcut() {
    execTime.set(System.currentTimeMillis());
  }

  @AfterReturning(value = "@annotation(com.wttch.wcbs.logs.annotations.Log)", returning = "ret")
  public void afterReturningPoint(JoinPoint point, Object ret) {
    long timestamp = System.currentTimeMillis();
    if (ret instanceof Logable) {

      Object[] args = point.getArgs();
      Class<?>[] argTypes = new Class[point.getArgs().length];
      for (int i = 0; i < args.length; i++) {
        argTypes[i] = args[i].getClass();
      }
      Method method = null;
      try {
        method = point.getTarget().getClass().getMethod(point.getSignature().getName(), argTypes);
        Log log = method.getAnnotation(Log.class);
        LogInfo<Logable, LogTemplate> logInfo = new LogInfo<Logable, LogTemplate>();
        logInfo.setLogable((Logable) ret);
        logInfo.setTemplate((LogTemplate) logTemplateManager.getLogTemplate(log.value()));
        Optional.ofNullable(
                getLogListener(
                    ret.getClass(), logTemplateManager.getLogTemplate(log.value()).getClass()))
            .ifPresent(logListener -> logListener.consume(logInfo));
      } catch (Exception e) {

      }
    }
  }
}
