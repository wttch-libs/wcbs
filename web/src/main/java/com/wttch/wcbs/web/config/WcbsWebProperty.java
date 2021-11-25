package com.wttch.wcbs.web.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wcbs.web")
@NoArgsConstructor
@Getter
@Setter
public class WcbsWebProperty {}
