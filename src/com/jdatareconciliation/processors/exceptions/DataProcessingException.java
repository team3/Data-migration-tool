package com.jdatareconciliation.processors.exceptions;

/**
 * The Class DataProcessingException.
 */
public class DataProcessingException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 6216760043150932991L;

  /**
   * Instantiates a new data processing exception.
   *
   * @param s the s
   */
  public DataProcessingException(String s) {
    super(s);
  }
  
  /**
   * Instantiates a new data processing exception.
   *
   * @param t the t
   */
  public DataProcessingException(Throwable t) {
    super(t);
  }
}
