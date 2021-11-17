package test;

import com.wttch.wcbs.data.jdbc.config.DataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("test.a")
public class TestApplication {
  public static void main(String[] args) {
    new DataSourceProperties();
    SpringApplication.run(TestApplication.class, args);
  }
}
