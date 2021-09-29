package com.wttch.wcbs.core;

public class BaseEntity<T extends BaseEntity<T, ID>, ID> {
  private ID id;
}
