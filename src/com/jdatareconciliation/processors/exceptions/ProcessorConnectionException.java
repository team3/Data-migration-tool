package com.jdatareconciliation.processors.exceptions;

/**
 * The Class ProcessorConnectionException.
 */
public class ProcessorConnectionException extends Exception {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1436719344036367142L;

  /**
   * Instantiates a new processor connection exception.
   *
   * @param s the s
   */
  public ProcessorConnectionException(String s) {
    super(s);
  }
  
  /**
   * Instantiates a new processor connection exception.
   *
   * @param t the t
   */
  public ProcessorConnectionException(Throwable t) {
    super(t);
  }
}
