package com.jdatareconciliation.processors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.jdatareconciliation.ProcessorConfiguration;
import com.jdatareconciliation.connection.ConnectionConfiguration;
import com.jdatareconciliation.processors.exceptions.DataProcessingException;
import com.jdatareconciliation.processors.exceptions.ProcessorConnectionException;

/**
 * File processor allows to user methods: getInputStream() and
 * getOutputStream();
 * 
 * @author Andrey Parkhomenko
 * 
 */
public class FileProcessor extends AbstractProcessor {
  
  /**
   * Instantiates a new file processor.
   *
   * @param connectionConfiguration the connection configuration
   * @param processorConfiguration the processor configuration
   */
  public FileProcessor(ConnectionConfiguration connectionConfiguration,
      ProcessorConfiguration processorConfiguration) {
    super(connectionConfiguration, processorConfiguration);
  }

  /** The Constant PROPERTY_INPUT_FILENAME. */
  private static final String PROPERTY_INPUT_FILENAME  = "input_filename";
  
  /** The Constant PROPERTY_INPUT_FILETYPE. */
  private static final String PROPERTY_INPUT_FILETYPE  = "input_filetype";
  
  /** The Constant PROPERTY_OUTPUT_FILENAME. */
  private static final String PROPERTY_OUTPUT_FILENAME = "output_filename";
  
  /** The Constant PROPERT_OUTPUT_FILETYPE. */
  private static final String PROPERT_OUTPUT_FILETYPE  = "output_filetype";
  
  /** The required attributes. */
  protected String[]          requiredAttributes       = new String[] {
      PROPERTY_INPUT_FILENAME, PROPERTY_INPUT_FILETYPE,
      PROPERTY_OUTPUT_FILENAME, PROPERT_OUTPUT_FILETYPE };

  /** The is. */
  private InputStream         is;

  /** The os. */
  private OutputStream        os;


  /* (non-Javadoc)
   * @see com.jdatareconciliation.processors.IProcessor#connect()
   */
  @Override
  public void connect()
      throws ProcessorConnectionException {

    openSourceConnection();

    openDestConnection();
  }


  /* (non-Javadoc)
   * @see com.jdatareconciliation.processors.IProcessor#process()
   */
  @Override
  public void process()
      throws DataProcessingException {

    if (getInputStream() != null && getOutputStream() != null) {
      // just copy content from input file to output file for test
      try {
        IOUtils.copy(getInputStream(), getOutputStream());
      } catch (IOException e) {
        throw new DataProcessingException(e);
      }
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jdatareconciliation.processors.IProcessor#disconnect()
   */
  @Override
  public void disconnect() throws ProcessorConnectionException {
    System.out.println("Call disconnect() | closing the input and output stream");
    IOUtils.closeQuietly(is);
    IOUtils.closeQuietly(os);
  }

  /**
   * Open source connection.
   * 
   * @throws ProcessorConnectionException
   *           the processor connection exception
   */
  protected void openSourceConnection() throws ProcessorConnectionException {
    System.out.println("open source connection | filename: "
        + PROPERTY_INPUT_FILENAME);
    // getting the name of the file
    String filename = getConnectionParameter(PROPERTY_INPUT_FILENAME);
    try {
      is = new BufferedInputStream(new FileInputStream(new File(filename)));
    } catch (FileNotFoundException e) {
      throw new ProcessorConnectionException(e);
    }
  }

  /**
   * Open dest connection.
   * 
   * @throws ProcessorConnectionException
   *           the processor connection exception
   */
  protected void openDestConnection() throws ProcessorConnectionException {
    System.out.println("open source connection | filename: " + PROPERTY_OUTPUT_FILENAME);
    String outputFileName = getConnectionParameter(PROPERTY_OUTPUT_FILENAME);

    if (StringUtils.isEmpty(outputFileName)) {
      throw new ProcessorConnectionException("Empty path of the output file");
    }

    // opening the output stream for writing
    try {
      os = new BufferedOutputStream(new FileOutputStream(new File(
          outputFileName)));
    } catch (FileNotFoundException e) {
      throw new ProcessorConnectionException(e);
    }
  }

  /**
   * Gets the input stream.
   * 
   * @return the input stream
   */
  protected InputStream getInputStream() {
    return this.is;
  }

  /**
   * Gets the output stream.
   * 
   * @return the output stream
   */
  protected OutputStream getOutputStream() {
    return this.os;
  }
  
  /* (non-Javadoc)
   * @see com.jdatareconciliation.processors.AbstractProcessor#getRequiredAttributes()
   */
  @Override
  public List<String> getRequiredAttributes() {
    return Arrays.asList(requiredAttributes);
  }
}
