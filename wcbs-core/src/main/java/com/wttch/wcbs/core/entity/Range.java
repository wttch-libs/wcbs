package com.wttch.wcbs.core.entity;

import lombok.Getter;

@Getter
public class Range<T> {
  protected final T begin;
  protected final T end;

  protected Range(T begin, T end) {
    this.begin = begin;
    this.end = end;
  }

  public static <T> Range<T> of(T begin, T end) {
    return new Range<>(begin, end);
  }
}
