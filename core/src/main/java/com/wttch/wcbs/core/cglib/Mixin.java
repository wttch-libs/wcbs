package com.wttch.wcbs.core.cglib;

import com.wttch.wcbs.core.exception.WcbsException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Mixin<T> implements MethodInterceptor {
  private T target;
  private List<Class<?>> delegateClasses = new LinkedList<>();
  private List<Object> delegateObjects = new LinkedList<>();

  public Mixin(T delegate) {
    this.target = delegate;
  }

  public Mixin<T> mixin(Class<?> clazz, Object obj) {
    if (clazz.isInstance(obj) && clazz.isInterface()) {
      delegateClasses.add(clazz);
      delegateObjects.add(obj);
    }

    throw new WcbsException(
        String.format(
            "不能混入对象%s到类型%s, 可能混入类型不为接口，或者混入对象不是混入类型的实例.", obj.toString(), clazz.getName()));
  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
      throws Throwable {
    Method sourceMethod;
    args = Optional.ofNullable(args).orElse(new Object[0]);
    Class<?>[] argsClass = new Class[args.length];
    for (int i = 0; i < args.length; i++) {
      argsClass[i] = args[i].getClass();
    }
    try {
      sourceMethod = obj.getClass().getMethod(method.getName(), argsClass);
      return sourceMethod.invoke(obj, args);
    } catch (NoSuchMethodException e) {
      try {
        for (int i = 0; i < delegateClasses.size(); i++) {
          sourceMethod = delegateClasses.get(i).getMethod(method.getName(), argsClass);
          return sourceMethod.invoke(delegateObjects.get(i), args);
        }
      } catch (NoSuchMethodException ignored) {
      }
      throw e;
    }
  }
}
