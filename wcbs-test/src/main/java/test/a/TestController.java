package test.a;

import com.wttch.wcbs.web.RespWrapper;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Resource TestService testService;

  @PostMapping("/test")
  public RespWrapper<String> test(@RequestBody Payload payload) {
    testService.test(payload, 1);
    return RespWrapper.ok("ok");
  }
}
