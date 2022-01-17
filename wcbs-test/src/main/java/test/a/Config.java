package test.a;

import com.wttch.wcbs.logs.LogTemplateManager;
import com.wttch.wcbs.logs.simple.EnumDefaultLogTemplateManager;
import com.wttch.wcbs.logs.simple.JsonEnumDefaultLogTemplateManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

@Configuration
public class Config {

    @Bean
    public LogTemplateManager defaultLogTemplateManager() {
       // return new EnumDefaultLogTemplateManager(LogInfo.class);
        return new JsonEnumDefaultLogTemplateManager(new DefaultResourceLoader().getResource("classpath:log.json"));
    }
}
