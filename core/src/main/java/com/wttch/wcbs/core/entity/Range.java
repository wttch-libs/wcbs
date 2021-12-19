package com.wttch.wcbs.core.entity;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * 表示一个范围，比如时间范围日期范围等
 *
 * @param <T> 范围的类型
 * @author wttch
 */
@Getter
public class Range<T> {
  /** 范围开始，可以为 null */
  @Nullable protected final T begin;
  /** 范围结束, 可以为 null */
  @Nullable protected final T end;

  protected Range(@Nullable T begin, @Nullable T end) {
    this.begin = begin;
    this.end = end;
  }
}
