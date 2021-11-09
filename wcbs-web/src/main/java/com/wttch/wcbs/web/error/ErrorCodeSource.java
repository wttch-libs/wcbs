package com.wttch.wcbs.web.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误等级
 *
 * @author wttch
 */
@Getter
@AllArgsConstructor
enum ErrorCodeSource {
  /** 用户错误 */
  USER('A'),
  /** 系统错误 */
  SYSTEM('B'),
  /** 第三方库错误 */
  API('C');

  private final char code;
}
