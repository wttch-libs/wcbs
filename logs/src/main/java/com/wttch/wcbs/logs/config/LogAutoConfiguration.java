package com.wttch.wcbs.logs.config;

import com.wttch.wcbs.logs.simple.DefaultLogListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wttch.wcbs.logs.component")
public class LogAutoConfiguration {
    @Bean
    public DefaultLogListener defaultLogListener() {
        return new DefaultLogListener();
    }
}
