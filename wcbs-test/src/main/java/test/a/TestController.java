package test.a;

import com.wttch.wcbs.web.RespWrapper;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Resource TestMapper testMapper;

  @GetMapping("/test")
  public RespWrapper<String> test() {
    testMapper.test();
    return RespWrapper.ok("ok");
  }
}
