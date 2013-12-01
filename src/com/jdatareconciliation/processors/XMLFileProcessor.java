package com.jdatareconciliation.processors;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.jdatareconciliation.ProcessorConfiguration;
import com.jdatareconciliation.connection.ConnectionConfiguration;
import com.jdatareconciliation.processors.exceptions.DataProcessingException;

/**
 * The Class XMLFileProcessor.
 */
public class XMLFileProcessor extends FileProcessor {

  /**
   * Instantiates a new xML file processor.
   * 
   * @param connectionConfiguration
   *          the connection configuration
   * @param processorConfiguration
   *          the processor configuration
   */
  public XMLFileProcessor(ConnectionConfiguration connectionConfiguration,
      ProcessorConfiguration processorConfiguration) {
    super(connectionConfiguration, processorConfiguration);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jdatareconciliation.processors.FileProcessor#process()
   */
  @Override
  public void process() throws DataProcessingException {
    Map<String, Object> sourceData = getInputData();
    
    for (Map.Entry<String, Object> e: sourceData.entrySet()) {
      System.out.println("Writing: " + e.getKey() + "-" + e.getValue());
      try {
        writeToStream(e.getKey() + "-" + e.getValue());
      } catch (IOException e1) {
        throw new DataProcessingException(e1);
      }
    }
  }
  
  /* (non-Javadoc)
   * @see com.jdatareconciliation.processors.AbstractProcessor#getRequiredAttributes()
   */
  @Override
  public List<String> getRequiredAttributes() {
    return super.getRequiredAttributes();
  }
}
