package com.wttch.common.reflect;

/**
 * 反射相关的工具类
 *
 * @author wttch
 */
public class Reflects {
  private Reflects() {}

  /**
   * 将给定对象列表转换为对象对应的类
   *
   * @param args 要转换的对象列表
   * @return 对象对应的类型列表
   */
  public static Class<?>[] toClasses(Object... args) {
    var classes = new Class<?>[args.length];
    for (int i = 0; i < args.length; i++) {
      classes[i] = args[i].getClass();
    }
    return classes;
  }
}
