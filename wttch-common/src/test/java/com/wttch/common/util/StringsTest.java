package com.wttch.common.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("ConstantConditions")
public class StringsTest {
  @Test
  public void testIsBlack() {
    Assertions.assertTrue(Strings.isBlack(null));
    Assertions.assertTrue(Strings.isBlack(""));
    Assertions.assertFalse(Strings.isBlack("wttch"));
  }

  @Test
  public void testIsNotBlack() {
    Assertions.assertFalse(Strings.isNotBlack(null));
    Assertions.assertFalse(Strings.isNotBlack(""));
    Assertions.assertTrue(Strings.isNotBlack("wttch"));
  }

  @Test
  public void testLength() {
    Assertions.assertEquals(Strings.length(null), 0);
    Assertions.assertEquals(Strings.length(""), 0);
    Assertions.assertEquals(Strings.length("wttch"), 5);
  }
}
