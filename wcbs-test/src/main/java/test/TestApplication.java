package test;

import com.wttch.wcbs.mybatis.property.DataSourceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {
  public static void main(String[] args) {
    new DataSourceProperty();
    SpringApplication.run(TestApplication.class, args);
  }
}
