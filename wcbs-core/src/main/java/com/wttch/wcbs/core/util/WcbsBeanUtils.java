package com.wttch.wcbs.core.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

/**
 * BeanUtils 扩展工具类，添加一些 Bean 复制的方法。
 *
 * @author wttch
 */
public class WcbsBeanUtils extends BeanUtils {
  private WcbsBeanUtils() {}

  /**
   * 获取指定对象的所有为空的属性
   *
   * @param source 指定的对象
   * @return 所有值为 null 的属性名称数组
   */
  private static String[] getNullPropertyNames(Object source) {
    var src = new BeanWrapperImpl(source);
    var pds = src.getPropertyDescriptors();
    Set<String> emptyNames = new HashSet<>();

    for (PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) {
        emptyNames.add(pd.getName());
      }
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }

  /**
   * 属性拷贝，使用 {@link #copyProperties(Object, Object, String...)}，忽略掉源对象所有为空的属性。
   *
   * @param source 源对象
   * @param target 目标对象
   * @throws BeansException 如果属性拷贝失败
   */
  public static void copyPropertiesIgnoreNullProperty(Object source, Object target)
      throws BeansException {
    copyProperties(source, target, getNullPropertyNames(source));
  }
}
