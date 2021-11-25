package com.wttch.wcbs.data.mybatis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Parameter {
  private Class<?> clazz;
  private Object value;
}
