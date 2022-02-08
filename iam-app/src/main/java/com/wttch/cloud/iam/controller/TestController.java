package com.wttch.cloud.iam.controller;

import com.wttch.cloud.iam.A;
import com.wttch.wcbs.logs.annotations.Log;
import com.wttch.wcbs.logs.util.LogUtils;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Log("test")
  @GetMapping
  public A test() {
    Object a = LogUtils.mixinLogable(new A("String"), List.of("A"), List.of("B", "C"));
    return (A) (a);
  }
}
