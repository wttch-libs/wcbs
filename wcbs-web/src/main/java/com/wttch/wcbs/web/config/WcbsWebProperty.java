package com.wttch.wcbs.web.config;

import com.wttch.wcbs.web.enums.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wcbs.web")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WcbsWebProperty {
  private JsonType json;
}
