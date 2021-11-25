package com.wttch.wcbs.data.jdbc.durid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * druid 内置页面展示druid的统计信息
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidStatViewServlet {
  /** Enable StatViewServlet, default false. */
  private boolean enabled = false;

  private String urlPattern = "/druid/";
  /** 允许通过的 ip */
  private String allow;
  /** 不允许通过的 ip */
  private String deny;
  /** 登录名 */
  private String loginUsername;
  /** 登录密码 */
  private String loginPassword;
  /** 是否允许重制计数器 */
  private String resetEnable;
}
