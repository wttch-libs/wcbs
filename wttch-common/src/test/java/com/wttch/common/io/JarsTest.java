package com.wttch.common.io;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JarsTest {

  @Test
  public void testIsJar() throws MalformedURLException {
    Assertions.assertTrue(Jars.isJar(new URL("jar:file:/A.jar!/org")));
  }

  @Test
  public void testJarPathFromUrl() throws MalformedURLException {
    Assertions.assertEquals(Jars.jarPathFormUrl(new URL("jar:file:/A.jar!/org")), "/A.jar");
  }
}
