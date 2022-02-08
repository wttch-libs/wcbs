package com.wttch.wcbs.logs.util;

import com.wttch.wcbs.logs.logs.OperatorLogable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import net.sf.cglib.proxy.*;

/**
 * 日志相关的工具类
 *
 * @author wttch
 */
public class LogUtils {
  private LogUtils() {}

  /**
   * 混入 OperatorLogable
   *
   * @param obj 要混入的对象
   * @param operatorArgs 混入的{@link OperatorLogable#operatorArgs()}的值
   * @param infoArgs 混入{@link OperatorLogable#infoArgs()}的值
   * @param <T> 混入对象的类型
   * @return 混入后的对象
   */
  public static <T> T mixinLogable(T obj, List<Object> operatorArgs, List<Object> infoArgs) {
    Object x =
        Mixin.createBean(new Object[] {obj, OperatorLogable.of(obj, operatorArgs, infoArgs)});
    System.out.println(x);
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(obj.getClass());
    enhancer.setInterfaces(new Class[] {OperatorLogable.class});
    enhancer.setCallback(
        new MethodInterceptor() {
          @Override
          public Object intercept(Object proxy1, Method method, Object[] args, MethodProxy proxy)
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
                sourceMethod = OperatorLogable.class.getMethod(method.getName(), argsClass);
                return sourceMethod.invoke(OperatorLogable.of(obj, operatorArgs, infoArgs), args);
              } catch (NoSuchMethodException ignored) {
              }
              throw e;
            }
          }
        });
    return (T) enhancer.create();
  }
}
