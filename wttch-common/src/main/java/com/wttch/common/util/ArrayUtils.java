package com.wttch.common.util;

import java.lang.reflect.Array;

/**
 * 数组相关的工具类
 *
 * @author wttch
 */
public class ArrayUtils {
  private ArrayUtils() {}

  /**
   * 使用 {@link System#arraycopy(Object, int, Object, int, int)} 来合并数组
   *
   * @param arrays 要合并的数组列表
   * @param <T> 数组内容类型
   * @return 合并完成的数组
   */
  @SuppressWarnings({"unchecked"})
  public static <T> T[] merge(T[]... arrays) {
    if (arrays.length == 0) {
      throw new IllegalArgumentException("不支持空数组合并！");
    }
    if (arrays.length == 1) {
      return arrays[0];
    }

    int length = 0;
    for (var array : arrays) {
      length += array.length;
    }
    T[] result = (T[]) Array.newInstance(arrays[0].getClass().getComponentType(), length);
    int begin = 0;
    for (var array : arrays) {
      System.arraycopy(array, 0, result, begin, array.length);
      begin += array.length;
    }
    return result;
  }
}
