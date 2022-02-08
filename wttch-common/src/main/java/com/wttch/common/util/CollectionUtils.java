package com.wttch.common.util;

import java.util.Collection;

/** 集合相关的工具类 */
public final class CollectionUtils {
  private CollectionUtils() {}

  /**
   * 判断集合是否为空(null 或者 empty)
   *
   * @param collection 要判断的集合
   * @return 如果集合为空(null 或者 empty)则返回 true， 否则 false
   */
  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * 判断集合是否不为空(null 或者 empty)
   *
   * @param collection 要判断的集合
   * @return 如果集合不为空(null 或者 empty)则返回 true， 否则 false
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return !isEmpty(collection);
  }
}
