package com.wttch.wcbs.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Map 相关的工具类
 *
 * @author wttch
 */
public class MapUtils {
  private MapUtils() {}

  /**
   * 将map进行映射，使用相同的key，对map内所有的value进行数据映射，生成一个新的map。
   *
   * @param sourceMap 源map
   * @param mapFunction map值映射函数
   * @param <T> 源数据类型
   * @param <R> 映射后的数据类型
   * @return value经过映射后的新的map
   */
  public static <T, R> Map<String, R> mapTo(Map<String, T> sourceMap, Function<T, R> mapFunction) {
    var resultMap = new HashMap<String, R>(sourceMap.size());
    sourceMap.forEach((key, value) -> resultMap.put(key, mapFunction.apply(value)));
    return resultMap;
  }
}
