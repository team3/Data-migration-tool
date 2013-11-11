package com.jdatareconciliation.processors.exceptions;

/**
 * The Class DataExtractorException.
 */
public class DataExtractorException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 623054948130066959L;

  /**
   * Instantiates a new data extractor exception.
   * 
   * @param s the s
   */
  public DataExtractorException(String s) {
    super(s);
  }

  /**
   * Instantiates a new data extractor exception.
   * 
   * @param t the t
   */
  public DataExtractorException(Throwable t) {
    super(t);
  }
}
