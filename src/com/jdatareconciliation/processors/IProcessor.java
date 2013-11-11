package com.jdatareconciliation.processors;

import java.util.List;

import com.jdatareconciliation.processors.exceptions.ConfigurationException;
import com.jdatareconciliation.processors.exceptions.DataProcessingException;
import com.jdatareconciliation.processors.exceptions.ProcessorConnectionException;

/**
 * The Interface IProcessor.
 */
public interface IProcessor {
  
  /**
   * Before connect.
   *
   * @param connectionConfiguration the connection configuration
   * @throws ConfigurationException the configuration exception
   */
  void beforeConnect() throws ConfigurationException;
  
  /**
   * Connect.
   *
   * @throws ProcessorConnectionException the processor connection exception
   */
  void connect() throws ProcessorConnectionException;
  

  /**
   * Extract source data.
   *
   * @throws DataProcessingException the data processing exception
   */
  void extractSourceData() throws DataProcessingException;
  
  /**
   * Process.
   *
   * @param c the configuration
   * @throws DataProcessingException the data processing exception
   */
  void process() throws DataProcessingException;
  
  /**
   * Disconnect.
   *
   * @throws ProcessorConnectionException the processor connection exception
   */
  void disconnect() throws ProcessorConnectionException;
  
  /**
   * Returns true if processor is connected to the datasource;
   * 
   * @return connection status
   */
  boolean isConnected();
  
  /**
   * Gets the required attributes.
   *
   * @return the required attributes
   */
  List<String> getRequiredAttributes();
}
