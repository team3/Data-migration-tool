/*
 * 
 */
package com.jdatareconciliation.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class Mapping.
 */
public class Mapping {

  /** The rules. */
  private List<Rule> rules;

  /**
   * Gets the rule by typ ruleename.
   *
   * @param typeName the type name
   * @return the rule by typ ruleename
   */
  public Rule getRuleByTypename(final String typeName) {
    for (Rule rule: rules) {
      if (StringUtils.equals(typeName, rule.getType())) {
        return rule;
      }
    }
    
    return null;
  }
  /**
   * The Class Rule.
   */
  public static class Rule {
    
    /** The Constant TYPE_MAPPING. */
    public static final String TYPE_MAPPING = "mapping";
    
    /** The Constant TYPE_VALIDATION. */
    public static final String TYPE_VALIDATION = "validation";
    
    /** The Constant TYPE_CUSTOM. */
    public static final String TYPE_CUSTOM = "custom";
    
    /**
     * The Class Fieldset.
     */
    public static class Fieldset {
      /** The colums separator. */
      String columsSeparator;
      
      /**
       * The Class Attribute.
       */
      public static class Attribute {
        
        /** The Constant TYPE_SIGNLE_KEY. */
        public static final int TYPE_SIGNLE_KEY = 0x1;
        
        /** The Constant TYPE_MULTIPLE_KEY. */
        public static final int TYPE_MULTIPLE_KEY = 0x2;
        
        /** The source. */
        String source;

        /** The dest. */
        String dest;

        /** The extractor. */
        String extractor;

        /** The type. */
        int type;
        
        /**
         * Gets the source.
         * 
         * @return the source
         */
        public String getSource() {
          return source;
        }

        /**
         * Gets the dest.
         * 
         * @return the dest
         */
        public String getDest() {
          return dest;
        }

        /**
         * Sets the source.
         * 
         * @param source
         *          the source to set
         */
        public void setSource(final String source) {
          this.source = source;
        }

        /**
         * Sets the dest.
         * 
         * @param dest
         *          the dest to set
         */
        public void setDest(String dest) {
          this.dest = dest;
        }

        /**
         * @return the extractor
         */
        public String getExtractor() {
          return extractor;
        }

        /**
         * @param extractor
         *          the extractor to set
         */
        public void setExtractor(String extractor) {
          this.extractor = extractor;
        }

        /**
         * @return the type
         */
        public int getType() {
          return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(int type) {
          this.type = type;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
          StringBuilder builder = new StringBuilder();
          builder.append("Attribute [source=");
          builder.append(source);
          builder.append(", dest=");
          builder.append(dest);
          builder.append(", extractor=");
          builder.append(extractor);
          builder.append(", type=");
          builder.append(type);
          builder.append("]");
          return builder.toString();
        }
      }

      /** The attributes. */
      List<Attribute> attributes;

      /**
       * Gets the attributes.
       * 
       * @return the attributes
       */
      public List<Attribute> getAttributes() {
        if (attributes == null) {
          attributes = new ArrayList<Attribute>();
        }
        
        return attributes;
      }

      /**
       * Sets the attributes.
       * 
       * @param attributes
       *          the attributes to set
       */
      public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
      }

      /**
       * @return the columsSeparator
       */
      public String getColumsSeparator() {
        return columsSeparator;
      }

      /**
       * @param columsSeparator the columsSeparator to set
       */
      public void setColumsSeparator(String columsSeparator) {
        this.columsSeparator = columsSeparator;
      }
    }

    /** The type. */
    String   type;

    /** The impl class. */
    String   implClass;

    /** The fieldset. */
    Fieldset fieldset;

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public String getType() {
      return type;
    }

    /**
     * Gets the impl class.
     * 
     * @return the implClass
     */
    public String getImplClass() {
      return implClass;
    }

    /**
     * Gets the fieldset.
     * 
     * @return the fieldset
     */
    public Fieldset getFieldset() {
      return fieldset;
    }

    /**
     * Sets the type.
     * 
     * @param type
     *          the type to set
     */
    public void setType(String type) {
      this.type = type;
    }

    /**
     * Sets the impl class.
     * 
     * @param implClass
     *          the implClass to set
     */
    public void setImplClass(String implClass) {
      this.implClass = implClass;
    }

    /**
     * Sets the fieldset.
     * 
     * @param fieldset
     *          the fieldset to set
     */
    public void setFieldset(Fieldset fieldset) {
      this.fieldset = fieldset;
    }

  }

  /**
   * @return the rules
   */
  public List<Rule> getRules() {
    if (rules == null) {
      rules = new ArrayList<Rule>();
    }
    
    return rules;
  }

  /**
   * @param rules the rules to set
   */
  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }

  /**
   * The Class Validation.
   */
  private class Validation {

    /** The type. */
    String type;

    /** The value. */
    String value;

  }
}
