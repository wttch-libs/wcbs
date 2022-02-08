package com.wttch.common.reflect;

import com.wttch.common.util.ArrayUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * 使用动态代理混入对象，可以简单实现多继承类似的效果。
 *
 * <p>要注意，出现方法签名一样的方法（方法名和参数列表都一样）时只会执行找到的第一个方法，并且无法保证会执行那个。
 *
 * @author wttch
 */
public class Mixin<T> implements InvocationHandler {
  /** 要混入的对象 */
  private final T source;
  /** 混入的接口列表 */
  private final List<Class<?>> mixinClasses = new LinkedList<>();
  /** 混入的对象 */
  private final Map<Class<?>, Object> mixinObject = new HashMap<>();

  public Mixin(T source) {
    this.source = source;
  }

  /**
   * 混入
   *
   * @param mixinClass 混入的数据类型
   * @param entity 混入的实体
   * @param <I> 混入接口的类型
   * @param <R> 混入的对象类型
   * @return 自身，可以链式调用
   */
  public <I, R extends I> Mixin<T> mixin(Class<I> mixinClass, R entity) {
    this.mixinClasses.add(mixinClass);
    this.mixinObject.put(mixinClass, entity);
    return this;
  }

  /**
   * 构建动态代理对象
   *
   * @return 动态代理的对象
   */
  @SuppressWarnings("unchecked")
  public T build() {
    return (T)
        Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
            ArrayUtils.merge(
                mixinClasses.toArray(new Class<?>[0]), source.getClass().getInterfaces()),
            this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Method sourceMethod;
    args = Optional.ofNullable(args).orElse(new Object[0]);
    Class<?>[] argsClass = new Class[args.length];
    for (int i = 0; i < args.length; i++) {
      argsClass[i] = args[i].getClass();
    }
    try {
      sourceMethod = source.getClass().getMethod(method.getName(), argsClass);
      return sourceMethod.invoke(source, args);
    } catch (NoSuchMethodException e) {
      for (Class<?> clazz : mixinClasses) {
        try {
          sourceMethod = clazz.getMethod(method.getName(), argsClass);
          return sourceMethod.invoke(mixinObject.get(clazz), args);
        } catch (NoSuchMethodException ignored) {
        }
      }
      throw e;
    }
  }
}
