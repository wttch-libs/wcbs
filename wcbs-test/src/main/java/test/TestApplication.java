package test;

import com.wttch.wcbs.mybatis.property.DataSourceProperty;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("test")
public class TestApplication {
  public static void main(String[] args) {
    new DataSourceProperty();
    SpringApplication.run(TestApplication.class, args);
  }
}
