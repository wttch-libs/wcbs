package com.wttch.cloud.iam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class A {
  @Getter @Setter private String test;

  private String test2;

  public A(String test) {
    this.test = test;
    test2 = test + test;
  }
}
