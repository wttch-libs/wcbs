package test.a;

import com.wttch.wcbs.logs.annotations.Log;
import com.wttch.wcbs.logs.util.LogUtils;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
  @Resource TestMapper testMapper;

  @Transactional
  @Log("test")
  public TestMapper test(Payload items, Integer x) {
    try {
      testMapper.test(items, x);
      var test = LogUtils.mixinLogable(testMapper, List.of("1", "2"), List.of(222));
      return test;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
