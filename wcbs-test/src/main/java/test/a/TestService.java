package test.a;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
  @Resource TestMapper testMapper;

  @Transactional
  String test(Payload items, Integer x) {
    try {
      return testMapper.test(items, x);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
