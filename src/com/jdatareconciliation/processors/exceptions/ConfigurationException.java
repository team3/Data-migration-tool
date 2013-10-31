package com.jdatareconciliation.processors.exceptions;

/**
 * The Class ConfigurationException.
 */
public class ConfigurationException extends Exception {
  

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 218853386538612818L;

  /**
   * Instantiates a new configuration exception.
   *
   * @param s the s
   */
  public ConfigurationException(String s) {
    super(s);
  }
  
  /**
   * Instantiates a new configuration exception.
   *
   * @param t the t
   */
  public ConfigurationException(Throwable t) {
    super(t);
  }

}
