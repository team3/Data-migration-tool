package com.jdatareconciliation.processors;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.jdatareconciliation.ProcessorConfiguration;
import com.jdatareconciliation.connection.ConnectionConfiguration;
import com.jdatareconciliation.processors.exceptions.ConfigurationException;
import com.jdatareconciliation.processors.exceptions.DataProcessingException;
import com.jdatareconciliation.processors.exceptions.ProcessorConnectionException;

/**
 * The Class FileProcessor.
 */
public abstract class AbstractProcessor implements IProcessor {
  
  /** The connection. */
  protected ConnectionConfiguration connectionConfiguration;
  
  /** The configuration. */
  protected ProcessorConfiguration processorConfiguration;
  
  /** The is connected. */
  private boolean isConnected;
  
  /** The required attributes. */
  protected String[] requiredAttributes = new String[]{};

  /**
   * Instantiates a new abstract processor.
   *
   * @param connectionConfiguration the connection configuration
   * @param processorConfiguration the processor configuration
   */
  public AbstractProcessor(ConnectionConfiguration connectionConfiguration, ProcessorConfiguration processorConfiguration) {
    super();
    this.connectionConfiguration = connectionConfiguration;
    this.processorConfiguration = processorConfiguration;
  }
  /**
   * Before connect.
   * 
   * @param connection
   *          the connection
   * @throws ProcessorConnectionException
   *           the processor connection exception
   */
  public void beforeConnect()
      throws ConfigurationException {
    System.out.println("Abstract processor | beforeConnect | connectionConfiguration = " + connectionConfiguration);

    if (!isConfigurationValid()) {
      throw new ConfigurationException(String.format(
          "Connection configuration is not valid, mandatory parameters: %s",
          stringOfMandatoryFields()));
    }
  }
  
  /**
   * Do the main logic of the data processing.
   *
   * @param connectionConfiration the connection confiration
   * @param processorConfiguration the processor configuration
   * 
   * @throws ConfigurationException the configuration exception
   * @throws ProcessorConnectionException the processor connection exception
   * @throws DataProcessingException the data processing exception
   */
  public void doLogic()
      throws ConfigurationException, ProcessorConnectionException,
      DataProcessingException {
    System.out.println("Abstract processor | doLogic");
    beforeConnect();
    connect();
    process();
    disconnect();
  }
  
  /**
   * Checking whether the configuration contains specified parameters for each
   * of the connection implementation.
   * 
   * @return true, if is configuration valid
   */
  protected boolean isConfigurationValid() {
    System.out.println("connection configuration params: " + connectionConfiguration);
    boolean result = true;
    if (this.connectionConfiguration != null && this.connectionConfiguration.getParameters() != null) {
      for (String mField : getRequiredAttributes()) {
        System.out.println("Mandatory field: " + mField);
        
        Properties parameters = connectionConfiguration.getParameters();
        System.out.println("key is contained: " + parameters.containsKey(mField));
        result &= parameters.containsKey(mField);
      }
    }
    
    return result;
  }
  
  /**
   * Gets the connection configuration parameter's value.
   *
   * @param parameter the parameter
   * @return the connection parameter
   */
  protected String getConnectionParameter(String parameter) {
    if (this.connectionConfiguration != null) {
      Properties connectioParameters = this.connectionConfiguration.getParameters();
      if (connectioParameters != null && connectioParameters.containsKey(parameter)) {
        return connectioParameters.getProperty(parameter);
      }
    }
    
    return null;
  }
  
  /* (non-Javadoc)
   * @see com.jdatareconciliation.processors.IProcessor#isConnected()
   */
  public boolean isConnected() {
    return this.isConnected;
  }
  
  /**
   * Sets the connected.
   *
   * @param value the new connected
   */
  public void setConnected(boolean value) {
    this.isConnected = value;
  }
  
  /**
   * Gets the required attributes.
   *
   * @return the required attributes
   */
  public List<String> getRequiredAttributes() {
    return Arrays.asList(requiredAttributes);
  }
  
  
  /**
   * String of mandatory fields.
   *
   * @return the string
   */
  protected String stringOfMandatoryFields() {
    return getRequiredAttributes().toString();
  }
}
