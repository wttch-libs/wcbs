package com.wttch.common.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FilesTest {

  @Test
  public void testIsFile() throws MalformedURLException {
    Assertions.assertTrue(Files.isFile(new URL("file:/A.jar")));
    Assertions.assertFalse(Files.isFile(new URL("jar:file:/A.jar!/org")));
  }

  @Test
  public void testIsClass() {
    Assertions.assertFalse(Files.isClass(""));
    Assertions.assertFalse(Files.isClass(null));
    Assertions.assertFalse(Files.isClass("a.txt"));
    Assertions.assertTrue(Files.isClass("a.class"));
  }

  @Test
  public void testIsAnonymousInnerClass() {
    Assertions.assertFalse(Files.isAnonymousInnerClass(null));
    Assertions.assertFalse(Files.isAnonymousInnerClass(""));
    Assertions.assertFalse(Files.isAnonymousInnerClass("A"));
    Assertions.assertFalse(Files.isAnonymousInnerClass("A.class"));
    Assertions.assertFalse(Files.isAnonymousInnerClass("A.test"));
    Assertions.assertTrue(Files.isAnonymousInnerClass("A$1.class"));
    Assertions.assertTrue(Files.isAnonymousInnerClass("A.A$1.class"));
  }

  @Test
  public void testPackage2Path() {
    Assertions.assertEquals(Files.package2Path("A"), "A");
    Assertions.assertEquals(Files.package2Path("A.A"), "A/A");
  }

  @Test
  public void testPath2Package() {
    Assertions.assertEquals(Files.path2Package("A"), "A");
    Assertions.assertEquals(Files.path2Package("A/A"), "A.A");
  }
}
