package com.wttch.wcbs.core;

import lombok.Data;

@Data
public class BaseEntity<T extends BaseEntity<T, ID>, ID> {
  private ID id;
}
