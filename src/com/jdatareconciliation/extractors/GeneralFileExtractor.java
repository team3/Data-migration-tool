package com.jdatareconciliation.extractors;

import java.io.InputStream;

import com.jdatareconciliation.configuration.Mapping;

/**
 * The Class GeneralFileExtractor.
 */
public abstract class GeneralFileExtractor extends AbstractExtractor {

  /** source of the data represeting by the link to input stream. */
  private InputStream is;

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
  }

  /**
   * Gets the input stream.
   *
   * @return the input stream
   */
  protected InputStream getInputStream() {
    return this.is;
  }
}
