package test.a;

import com.wttch.wcbs.core.entity.DateRange;
import com.wttch.wcbs.web.RespWrapper;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TestController {
  @Resource TestService testService;

  @PostMapping("/test")
  public RespWrapper<DateRange> test(@RequestBody Payload payload) {
    testService.test(payload, 1);
    return RespWrapper.ok(DateRange.of(LocalDate.now(), LocalDate.now()));
  }
}
