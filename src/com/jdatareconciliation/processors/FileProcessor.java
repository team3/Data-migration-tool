package com.jdatareconciliation.processors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.jdatareconciliation.ProcessorConfiguration;
import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.connection.ConnectionConfiguration;
import com.jdatareconciliation.extractors.IExtractor;
import com.jdatareconciliation.processors.exceptions.DataExtractorException;
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
  protected InputStream         is;

  /** The os. */
  protected OutputStream        os;


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
   * @see com.jdatareconciliation.processors.IProcessor#extractSourceData()
   */
  @Override
  public void extractSourceData() throws DataProcessingException{
    // using processor configuration extract the source data
    /*
     * Configuration should be valid. Validation process should be performed before connecttion to the datasource.
     */
    Mapping.Rule rule  = getProcessorConfiguration().getMapping().getRuleByTypename(Mapping.Rule.TYPE_MAPPING);
    String classname = rule.getImplClass();
    // invoking the extractor's implementation
    Class<?> clazz;
    try {
      clazz = Class.forName(classname);

      String datasourceType = "file";
      // TODO get datasource type from the configuration
      if (StringUtils.equals("file", datasourceType)) {
        Constructor<?> con = clazz.getConstructor(Mapping.Rule.Fieldset.class, InputStream.class);
        IExtractor extractor = (IExtractor) con.newInstance(rule.getFieldset(), getInputStream());
        Map<String, Object> data = extractor.extract();
        
        System.out.println("extracted data: " + data.size());
        
        setInputData(data);
      }
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataExtractorException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
        + getConnectionParameter(PROPERTY_INPUT_FILENAME));
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
    System.out.println("open source connection | filename: " + getConnectionParameter(PROPERTY_OUTPUT_FILENAME));
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
  
  /**
   * Write to stream.
   *
   * @param is the is
   * @throws IOException 
   */
  protected void writeToStream(Object data) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getOutputStream()));
    bw.write(data.toString());
  }
}
