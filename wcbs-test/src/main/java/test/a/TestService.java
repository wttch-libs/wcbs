package test.a;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.wttch.wcbs.logs.entity.Logable;
import com.wttch.wcbs.logs.annotations.Log;
import com.wttch.wcbs.logs.util.LogUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {
  @Resource TestMapper testMapper;

  @Transactional
  @Log("test")
  public TestMapper test(Payload items, Integer x) {
    try {
      testMapper.test(items, x);
      var test = LogUtils.minxinLogable(testMapper, List.of("1", "2"), List.of(222));
      return test;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
