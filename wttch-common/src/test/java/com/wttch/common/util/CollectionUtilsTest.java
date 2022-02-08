package com.wttch.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CollectionUtilsTest {
  @Test
  public void testIsEmpty() {
    Assertions.assertTrue(CollectionUtils.isEmpty(null));
    Assertions.assertTrue(CollectionUtils.isEmpty(List.of()));
    Assertions.assertFalse(CollectionUtils.isEmpty(List.of(1)));
  }
}
