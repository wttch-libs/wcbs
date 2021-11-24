package test.a;

import com.wttch.wcbs.web.RespWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
  @Resource TestMapper testMapper;

  @PostMapping("/test")
  public RespWrapper<String> test(@RequestBody Payload payload) {
    testMapper.test(payload);
    return RespWrapper.ok("ok");
  }
}
