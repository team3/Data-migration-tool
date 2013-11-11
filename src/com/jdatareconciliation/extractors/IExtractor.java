package com.jdatareconciliation.extractors;

import java.util.Map;

import com.jdatareconciliation.processors.exceptions.DataExtractorException;

/**
 * The Interface IExtractor.
 */
public interface IExtractor {
  
  /**
   * Extract.
   *
   * @return the map
   * @throws DataExtractorException the data extractor exception
   */
  Map<String, Object> extract() throws DataExtractorException;
}
