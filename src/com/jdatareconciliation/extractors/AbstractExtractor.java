package com.jdatareconciliation.extractors;

import java.util.Map;

import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.processors.exceptions.DataExtractorException;

/**
 * The Class AbstractExtractor.
 */
public abstract class AbstractExtractor implements IExtractor {

  /** The fieldset. */
  protected Mapping.Rule.Fieldset fieldset;

  /**
   * Instantiates a new abstract extractor.
   * 
   * @param fieldset
   *          the fieldset
   */
  public AbstractExtractor(Mapping.Rule.Fieldset fieldset) {
    this.fieldset = fieldset;
  }

  /**
   * Do logic.
   *
   * @return the map
   * @throws DataExtractorException the data extractor exception
   */
  public Map<String, Object> doLogic() throws DataExtractorException {
    return extract();
  }

  /**
   * Gets the fieldset.
   * 
   * @return the fieldset
   */
  protected Mapping.Rule.Fieldset getFieldset() {
    return fieldset;
  }
}
