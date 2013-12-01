package com.jdatareconciliation.extractors;

import java.io.InputStream;

import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.processors.exceptions.DataExtractorException;

/**
 * The Class GeneralFileExtractor.
 */
public abstract class GeneralFileExtractor extends AbstractExtractor {

  /** source of the data represeting by the link to input stream. */
  protected InputStream is;

  /**
   * Instantiates a new general file extractor.
   *
   * @param fieldset the fieldset
   */
  public GeneralFileExtractor(Mapping.Rule.Fieldset fieldset) {
    super(fieldset);
  }

  /**
   * Instantiates a new general file extractor.
   *
   * @param fieldset the fieldset
   * @param is the is
   */
  public GeneralFileExtractor(Mapping.Rule.Fieldset fieldset, InputStream is) {
    super(fieldset);
    /* setting the input stream */
    this.is = is;
  }

  /**
   * Gets the input stream.
   *
   * @return the input stream
   */
  protected InputStream getInputStream() {
    return is;
  }
  
  /**
   * Validate.
   *
   * @throws DataExtractorException the data extractor exception
   */
  protected void validate() throws DataExtractorException {
    if (getInputStream() == null) {
      throw new DataExtractorException("Input data stream is null");
    }

    if (getFieldset() == null) {
      throw new DataExtractorException(
          "Configuration of the fieldset is not defined");
    }

    if (getFieldset().getAttributes() == null) {
      throw new DataExtractorException(
          "No input items of the fielset have been found");
    }
  }
}
