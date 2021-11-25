package com.wttch.wcbs.core.exception;

/**
 * 框架异常，一般在编码测试阶段就会发现的异常，主要是一些框架功能不满足预期，应立即修复相关功能。
 *
 * @author wttch
 */
public class FrameworkException extends RuntimeException {

  public FrameworkException(String msg) {
    super(msg);
  }
}
