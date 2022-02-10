package com.wttch.common.util;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionUtilsTest {
  @Test
  public void testIsEmpty() {
    Assertions.assertTrue(CollectionUtils.isEmpty(null));
    Assertions.assertTrue(CollectionUtils.isEmpty(List.of()));
    Assertions.assertFalse(CollectionUtils.isEmpty(List.of(1)));
  }
}
