package com.wttch.common.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

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
