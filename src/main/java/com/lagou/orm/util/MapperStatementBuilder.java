package com.lagou.orm.util;

import com.lagou.orm.config.Configuration;
import com.lagou.orm.config.MapperStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class MapperStatementBuilder {

  private Configuration configuration;

  public MapperStatementBuilder(Configuration configuration) {
    this.configuration = configuration;
  }

  /**
   * 解析mapper文件 将每条sql转为一个MapperStatement
   * @param inputStream
   * @throws Exception
   */
  public void parseMapperStatement(InputStream inputStream) throws Exception {
    Document document = new SAXReader().read(inputStream);
    Element rootElement = document.getRootElement();
    String namespace = rootElement.attributeValue("namespace");
    List<Element> elements = rootElement.elements();
    for (Element e : elements) {
      MapperStatement mapperStatement = new MapperStatement();
      mapperStatement.setNamespace(namespace);
      mapperStatement.setSqlId(e.attributeValue("id"));
      mapperStatement.setParameterType(e.attributeValue("parameterType"));
      mapperStatement.setResultType(e.attributeValue("resultType"));
      mapperStatement.setSqlText(e.getTextTrim());
      String key = namespace + "." + e.attributeValue("id");
      System.out.println("mapperStatement key:" + key);
      this.configuration.getMapperStatementMap().put(key, mapperStatement);
    }
  }
}