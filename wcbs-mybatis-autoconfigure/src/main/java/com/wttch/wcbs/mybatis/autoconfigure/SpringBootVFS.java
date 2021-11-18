package com.wttch.wcbs.mybatis.autoconfigure;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 利用 SpringBoot 的 ResourcePatternResolver 进行xml等数据的读取.
 *
 * @author wttch
 */
public class SpringBootVFS extends VFS {

  private final ResourcePatternResolver resourceResolver;

  public SpringBootVFS() {
    this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
  }

  /** 如果VFS实现对当前环境有效，则返回 true */
  @Override
  public boolean isValid() {
    return true;
  }

  /**
   * 递归列出作为 URL 标识的资源的子级的所有资源的完整资源路径.
   *
   * @param url 标识要列出的资源的 URL
   * @param path 由 URL 标识的资源的路径, 通常, 这是传递给getResources(String)以获取资源 URL 的值.
   * @return 包含子资源名称的列表
   * @throws IOException 如果发生 I/O 错误
   */
  @Override
  protected List<String> list(URL url, String path) throws IOException {
    String urlString = url.toString();
    String baseUrlString = urlString.endsWith("/") ? urlString : urlString.concat("/");
    Resource[] resources = resourceResolver.getResources(baseUrlString + "**/*.class");
    return Stream.of(resources)
        .map(resource -> preserveSubpackageName(baseUrlString, resource, path))
        .collect(Collectors.toList());
  }

  private static String preserveSubpackageName(
      final String baseUrlString, final Resource resource, final String rootPath) {
    try {
      return rootPath
          + (rootPath.endsWith("/") ? "" : "/")
          + resource.getURL().toString().substring(baseUrlString.length());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
