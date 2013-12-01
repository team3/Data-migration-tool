package com.jdatareconciliation.extractors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jdatareconciliation.configuration.Mapping.Rule.Fieldset;
import com.jdatareconciliation.configuration.Mapping.Rule.Fieldset.Attribute;
import com.jdatareconciliation.processors.exceptions.DataExtractorException;

/**
 * The Class CsvFileExractor.
 */
public class CsvFileExractor extends GeneralFileExtractor {

  /**
   * Instantiates a new csv file exractor.
   *
   * @param fieldset the fieldset
   * @param is the is
   */
  public CsvFileExractor(Fieldset fieldset, InputStream is) {
    super(fieldset, is);
  }

  /* (non-Javadoc)
   * @see com.jdatareconciliation.extractors.IExtractor#extract()
   */
  @Override
  public Map<String, Object> extract() throws DataExtractorException {
    validate();
    
    Map<String, Object> map = new HashMap<String, Object>(getFieldset().getAttributes().size());
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream()));
    
    String line;
    int c = 0;
    try {
      while ((line = reader.readLine()) != null) {
        List<String> values = Arrays.asList(
            StringUtils.split(line, getFieldset().getColumsSeparator()));
        if (++c == 1) {
          // first line
          
          /* column's name-based decision */
        }
        
        
      }
    } catch (IOException e) {
      throw new DataExtractorException(e);
    }
    
    return map;
  }

  @Override
  protected void validate() throws DataExtractorException {
    super.validate();
    
    if (ExtractingUtils.getMultipleAttributes(getFieldset()).size() > 1) {
      throw new DataExtractorException(
          "Configuration exception. Count of the 'attributes' nodes more then aalowed (1).");
    }
    
    if (StringUtils.isEmpty(getFieldset().getColumsSeparator())) {
      throw new DataExtractorException(
          "Configuration exception. Attribute 'column-separator' is not defined for the fieldset.");
    }
  }
  
}
