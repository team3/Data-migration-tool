package com.jdatareconciliation.connection;

import java.util.Properties;

/**
 * The Class Connection.
 */
public class ConnectionConfiguration {
  
  /** The parameters. */
  private Properties parameters;

  /**
   * Gets the parameters.
   *
   * @return the parameters
   */
  public Properties getParameters() {
    return parameters;
  }

  /**
   * Sets the parameters.
   *
   * @param parameters the parameters to set
   */
  public void setParameters(Properties parameters) {
    this.parameters = parameters;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ConnectionConfiguration [parameters=");
    builder.append(parameters);
    builder.append(", getClass()=");
    builder.append(getClass());
    builder.append(", hashCode()=");
    builder.append(hashCode());
    builder.append(", toString()=");
    builder.append(super.toString());
    builder.append("]");
    return builder.toString();
  }
}
