package com.wttch.common.reflect;

import com.wttch.common.exception.WttchException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 对指定的对象混入指定的接口。
 *
 * <p>java本身动态代理无法生成对象，对象又无法多继承，只能通过对象混入接口来实现类似的功能。
 *
 * @param <T> 要混入对象的类型
 * @author wttch
 */
public class Mixin<T> implements MethodInterceptor {
  private final T target;
  private final List<Class<?>> delegateClasses = new LinkedList<>();
  private final List<Object> delegateObjects = new LinkedList<>();

  public Mixin(T delegate) {
    this.target = delegate;
  }

  public Mixin<T> mixin(Class<?> clazz, Object obj) {
    if (clazz.isInstance(obj) && clazz.isInterface()) {
      delegateClasses.add(clazz);
      delegateObjects.add(obj);
      return this;
    } else {
      throw new WttchException(
          String.format(
              "不能混入对象%s到类型%s, 可能混入类型不为接口，或者混入对象不是混入类型的实例.", obj.toString(), clazz.getName()));
    }
  }

  public T create() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setInterfaces(delegateClasses.toArray(new Class[0]));
    enhancer.setCallback(this);
    return (T) enhancer.create();
  }

  @Override
  public Object intercept(Object proxy, Method method, Object[] args, MethodProxy proxyMethod)
      throws Throwable {
    Method sourceMethod;
    args = Optional.ofNullable(args).orElse(new Object[0]);
    Class<?>[] argsClass = new Class[args.length];
    for (int i = 0; i < args.length; i++) {
      argsClass[i] = args[i].getClass();
    }
    try {
      sourceMethod = target.getClass().getMethod(method.getName(), argsClass);
      return sourceMethod.invoke(target, args);
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
