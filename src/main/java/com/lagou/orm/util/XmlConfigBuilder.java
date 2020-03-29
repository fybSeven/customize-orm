package com.lagou.orm.util;

import com.lagou.orm.config.Configuration;
import com.lagou.orm.io.Resource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XmlConfigBuilder {

  public static Configuration paresConfig(InputStream inputStream) throws Exception {
    Configuration configuration = new Configuration();
    Document document = new SAXReader().read(inputStream);
    Element rootElement = document.getRootElement();
    List<Element> nodes = rootElement.selectNodes("//property");
    Properties properties = new Properties();
    for (Element n : nodes) {
      String name = n.attributeValue("name");
      String value = n.attributeValue("value");
      properties.setProperty(name, value);
    }

    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    String driver = properties.getProperty("driver");
    comboPooledDataSource.setDriverClass(driver);
    String url = properties.getProperty("url");
    comboPooledDataSource.setJdbcUrl(url);
    String username = properties.getProperty("username");
    comboPooledDataSource.setUser(username);
    String password = properties.getProperty("password");
    comboPooledDataSource.setPassword(password);
    System.out.println(driver);
    System.out.println(url);
    System.out.println(username);
    System.out.println(password);
    configuration.setDataSource(comboPooledDataSource);

    List<Element> mapperNodes = rootElement.selectNodes("//mapper");
    for (Element mapperNode : mapperNodes) {
      String resource = mapperNode.attributeValue("resource");
      InputStream mapperInputStream = Resource.getResourceAsStream(resource);
      MapperStatementBuilder mapperStatementBuilder = new MapperStatementBuilder(configuration);
      mapperStatementBuilder.parseMapperStatement(mapperInputStream);
    }
    return configuration;
  }
}