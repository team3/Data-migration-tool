package com.jdatareconciliation.configuration;

import java.util.List;

public class Mapping {
  private List<Rule> rules;
  
  private class Rule {
    String objectName;
    
    List<Item> items;
    
    private class Item {
      
    }
  }
  
  private class Validation {
    String type;
    String value;
    
  }
}
