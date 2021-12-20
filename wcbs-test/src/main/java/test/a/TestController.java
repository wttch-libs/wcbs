package test.a;

import com.wttch.wcbs.core.entity.DateTimeRange;
import com.wttch.wcbs.web.RespWrapper;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Resource TestService testService;

  @PostMapping("/test")
  public RespWrapper<DateTimeRange> test(@RequestBody Payload payload) {
    testService.test(payload, 1);
    return RespWrapper.ok(DateTimeRange.of(LocalDateTime.now(), LocalDateTime.now().plusDays(22)));
  }
}
