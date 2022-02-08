package com.wttch.cloud.iam;

import com.wttch.wcbs.logs.logs.OperatorLogTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Logs implements OperatorLogTemplate {
  /** 测试 */
  TEST("test", "测试", "测试【{}】", "测试【{}】测试【{}】");

  private final String key;
  private final String action;
  private final String operator;
  private final String info;
}
