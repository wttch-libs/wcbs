package test;

import com.wttch.wcbs.web.RespWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping("/test")
  public RespWrapper<String> test() {
    return RespWrapper.ok("ok");
  }
}
