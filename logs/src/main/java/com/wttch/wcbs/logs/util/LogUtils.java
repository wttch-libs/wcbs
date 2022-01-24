package com.wttch.wcbs.logs.util;

import com.wttch.common.reflect.Mixin;
import com.wttch.wcbs.logs.logs.OperatorLogable;
import java.util.List;

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
    return new Mixin<T>(obj)
        .mixin(OperatorLogable.class, OperatorLogable.of(obj, operatorArgs, infoArgs))
        .build();
  }
}
