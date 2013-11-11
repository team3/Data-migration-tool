package com.jdatareconciliation;

import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.connection.ConnectionConfiguration;

/**
 * The Class ProcessorConfiguration.
 */
public class ProcessorConfiguration {
  
  /** The source type. */
  private String                  sourceType;
  
  /** The destination type. */
  private String                  destinationType;

  /** The mapping. */
  private Mapping                 mapping;
  
  /** The connection. */
  private ConnectionConfiguration connection;

  /**
   * The Enum SOURCE_TYPES.
   */
  enum SOURCE_TYPES {
   DB, 
   XML, 
   CVS
  };

  /**
   * The Enum DEST_TYPES.
   */
  enum DEST_TYPES {
    DB
  }

  /**
   * Gets the source type.
   *
   * @return the sourceType
   */
  public String getSourceType() {
    return sourceType;
  }

  /**
   * Gets the destination type.
   *
   * @return the destinationType
   */
  public String getDestinationType() {
    return destinationType;
  }

  /**
   * Gets the mapping.
   *
   * @return the mapping
   */
  public Mapping getMapping() {
    return mapping;
  }

  /**
   * Gets the connection.
   *
   * @return the connection
   */
  public ConnectionConfiguration getConnection() {
    return connection;
  }

  /**
   * Sets the source type.
   *
   * @param sourceType the sourceType to set
   */
  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }

  /**
   * Sets the destination type.
   *
   * @param destinationType the destinationType to set
   */
  public void setDestinationType(String destinationType) {
    this.destinationType = destinationType;
  }

  /**
   * Sets the mapping.
   *
   * @param mapping the mapping to set
   */
  public void setMapping(Mapping mapping) {
    this.mapping = mapping;
  }

  /**
   * Sets the connection.
   *
   * @param connection the connection to set
   */
  public void setConnection(ConnectionConfiguration connection) {
    this.connection = connection;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProcessorConfiguration [sourceType=");
    builder.append(sourceType);
    builder.append(", destinationType=");
    builder.append(destinationType);
    builder.append(", mapping=");
    builder.append(mapping);
    builder.append(", connection=");
    builder.append(connection);
    builder.append("]");
    return builder.toString();
  };
}
