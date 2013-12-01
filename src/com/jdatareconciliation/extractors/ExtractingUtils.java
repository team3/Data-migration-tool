package com.jdatareconciliation.extractors;

import java.util.ArrayList;
import java.util.List;

import com.jdatareconciliation.configuration.Mapping;

/**
 * The Class ExtractingUtils.
 */
public class ExtractingUtils {
  
  /**
   * Gets the multiple attributes.
   *
   * @param fieldset the fieldset
   * @return the multiple attributes
   */
  public static List<Mapping.Rule.Fieldset.Attribute> getMultipleAttributes(Mapping.Rule.Fieldset fieldset) {
    
    List<Mapping.Rule.Fieldset.Attribute> attrs = new ArrayList<Mapping.Rule.Fieldset.Attribute>();
    
    for (Mapping.Rule.Fieldset.Attribute attr: fieldset.getAttributes()) {
      if (attr.getType() == Mapping.Rule.Fieldset.Attribute.TYPE_MULTIPLE_KEY) {
        attrs.add(attr);
      }
    }
    
    return attrs;
  }

}
