package test.a;

import com.wttch.wcbs.logs.JsonDefaultLogTemplateManager;
import com.wttch.wcbs.logs.LogTemplateManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

@Configuration
public class Config {

  @Bean
  public LogTemplateManager defaultLogTemplateManager() {
    // return new EnumDefaultLogTemplateManager(LogInfo.class);
    return new JsonDefaultLogTemplateManager(
        new DefaultResourceLoader().getResource("classpath:log.json"));
  }
}
