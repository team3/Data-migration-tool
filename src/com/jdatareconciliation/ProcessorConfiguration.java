package com.jdatareconciliation;

import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.connection.ConnectionConfiguration;

public class ProcessorConfiguration {
  private String sourceType;
  private String destinationType;
  
  private Mapping mapping;
  private ConnectionConfiguration connection;
  
  enum SOURCE_TYPES {DB, XML, CVS};
  enum DEST_TYPES {DB};
}
