package test;

import com.wttch.wcbs.mybatis.property.MultipleDataSourceProperty;
import com.wttch.wcbs.web.RespWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
  @Resource
  MultipleDataSourceProperty multipleDataSourceProperty;

  @GetMapping("/test")
  public RespWrapper<String> test() {
    return RespWrapper.ok("ok");
  }
}
