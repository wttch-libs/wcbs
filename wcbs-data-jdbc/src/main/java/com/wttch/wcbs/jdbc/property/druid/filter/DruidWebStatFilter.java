package com.wttch.wcbs.jdbc.property.druid.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * web 页面的过滤器
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidWebStatFilter {
  /** Enable WebStatFilter, default false. */
  private boolean enabled;

  private String urlPattern;
  private String exclusions = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*";
  private String sessionStatMaxCount;
  private String sessionStatEnable;
  private String principalSessionName;
  private String principalCookieName;
  private String profileEnable;
}
