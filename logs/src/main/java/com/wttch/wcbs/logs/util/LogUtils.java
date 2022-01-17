package com.wttch.wcbs.logs.util;

import com.wttch.common.reflect.Mixin;
import com.wttch.wcbs.logs.simple.DefaultLogable;

import java.util.List;

public class LogUtils {
  public static <T> T minxinLogable(T obj, List<Object> operatorArgs, List<Object> infoArgs) {
    return new Mixin<T>(obj)
        .mixin(DefaultLogable.class, DefaultLogable.of(obj, operatorArgs, infoArgs))
        .build();
  }
}
