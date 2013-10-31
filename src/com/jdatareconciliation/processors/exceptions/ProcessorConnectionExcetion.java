package com.jdatareconciliation.processors.exceptions;

/**
 * The Class ProcessorConnectionExcetion.
 */
public class ProcessorConnectionExcetion extends Exception {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7100994855227759644L;

  /**
   * Instantiates a new processor connection excetion.
   *
   * @param s the s
   */
  public ProcessorConnectionExcetion(String s) { 
    super(s);
  }

  /**
   * Instantiates a new processor connection excetion.
   *
   * @param t the t
   */
  public ProcessorConnectionExcetion(Throwable t) {
    super(t);
  }
}
