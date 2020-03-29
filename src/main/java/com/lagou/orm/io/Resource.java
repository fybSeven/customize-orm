package com.lagou.orm.io;

import java.io.InputStream;

/**
 * 解析 SqlMapperConfig.xml文件
 */
public class Resource {
  public static InputStream getResourceAsStream(String path) {
    return Resource.class.getClassLoader().getResourceAsStream(path);
  }
}