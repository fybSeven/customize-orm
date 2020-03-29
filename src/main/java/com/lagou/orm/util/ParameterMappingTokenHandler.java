package com.lagou.orm.util;

import java.util.ArrayList;
import java.util.List;

public class ParameterMappingTokenHandler implements TokenHandler {

  private List<ParameterMapping> parameterMappings = new ArrayList();

  @Override
  public String handleToken(String content) {
    this.parameterMappings.add(buildParameterMapping(content));
    return "?";
  }

  private ParameterMapping buildParameterMapping(String content) {
    ParameterMapping parameterMapping = new ParameterMapping(content);
    return parameterMapping;
  }

  public List<ParameterMapping> getParameterMappings() {
    return this.parameterMappings;
  }

  public void setParameterMappings(List<ParameterMapping> parameterMappings) {
    this.parameterMappings = parameterMappings;
  }
}