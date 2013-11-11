package com.jdatareconciliation.test;

import java.util.Properties;

import com.jdatareconciliation.ProcessorConfiguration;
import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.configuration.Mapping.Rule;
import com.jdatareconciliation.connection.ConnectionConfiguration;
import com.jdatareconciliation.processors.XMLFileProcessor;
import com.jdatareconciliation.processors.exceptions.ConfigurationException;
import com.jdatareconciliation.processors.exceptions.DataProcessingException;
import com.jdatareconciliation.processors.exceptions.ProcessorConnectionException;

/**
 * The Class Test.
 */
public class Test {
  
  /** The Constant REPOSITORY_URI_PREFIX. */
  public static final String REPOSITORY_URI_PREFIX = "res/";
  
  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
    Properties connectionProps = new Properties();
    
    connectionProps.setProperty("input_filename", REPOSITORY_URI_PREFIX + "in.xml");
    connectionProps.setProperty("input_filetype", "txt");

    connectionProps.setProperty("output_filename", REPOSITORY_URI_PREFIX + "out.txt");
    connectionProps.setProperty("output_filetype", "txt");
    
    connectionConfiguration.setParameters(connectionProps);
    
    ProcessorConfiguration processorConfiguration = new ProcessorConfiguration();
    
    Mapping mapping = new Mapping();
    Mapping.Rule rule = new Mapping.Rule();
    rule.setType(Mapping.Rule.TYPE_MAPPING);
    // creating the fieldset
    Mapping.Rule.Fieldset fieldset = new Mapping.Rule.Fieldset();
    Mapping.Rule.Fieldset.Attribute attribute = new Mapping.Rule.Fieldset.Attribute();
    attribute.setSource("attr1");
    attribute.setDest("dest1");
    // TODO
    attribute.setExtractor("//book[author='Neal Stephenson']/title/text()");
    fieldset.getAttributes().add(attribute);
    rule.setFieldset(fieldset);
    rule.setImplClass("com.jdatareconciliation.extractors.XMLDataExtractor");
    mapping.getRules().add(rule);
    processorConfiguration.setMapping(mapping);
    
    XMLFileProcessor processor = new XMLFileProcessor(connectionConfiguration, processorConfiguration);
    
    try {
      processor.doLogic();
    } catch (ProcessorConnectionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
