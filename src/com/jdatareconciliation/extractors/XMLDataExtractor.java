package com.jdatareconciliation.extractors;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jdatareconciliation.configuration.Mapping;
import com.jdatareconciliation.processors.exceptions.DataExtractorException;

/**
 * The Class XMLDataExtractor.
 */
public class XMLDataExtractor extends GeneralFileExtractor {

  /**
   * Instantiates a new xML data extractor.
   * 
   * @param fieldset
   *          the fieldset
   */
  public XMLDataExtractor(Mapping.Rule.Fieldset fieldset, InputStream is) {
    super(fieldset, is);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jdatareconciliation.extractors.IExtractor#extract()
   */
  @Override
  public Map<String, Object> extract() throws DataExtractorException {
    validate();

    Map<String, Object> map = new HashMap<String, Object>(getFieldset()
        .getAttributes().size());

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(getInputStream());

      XPathFactory xpathFactory = XPathFactory.newInstance();
      XPath xpath = xpathFactory.newXPath();
      
      String value = null;
      
      for (Mapping.Rule.Fieldset.Attribute attribute : getFieldset().getAttributes()) {
        value = extraxtValue(document, attribute, xpath);

        map.put(attribute.getSource(), value);
      }
    } catch (ParserConfigurationException e) {
      throw new DataExtractorException(e);
    } catch (SAXException e) {
      throw new DataExtractorException(e);
    } catch (IOException e) {
      throw new DataExtractorException(e);
    } catch (XPathExpressionException e) {
      throw new DataExtractorException(e);
    }

    return map;
  }

  /**
   * Extraxt value.
   *
   * @param document the document
   * @param attribute the attribute
   * @return the string
   * @throws XPathExpressionException 
   */
  private String extraxtValue(Document document, Mapping.Rule.Fieldset.Attribute attribute, XPath xpath) 
      throws DataExtractorException, XPathExpressionException {
    
    XPathExpression ex = null;
    Object exResult = null;
    String value = null;
    
    if (attribute.getExtractor() != null) {
      ex = xpath.compile(attribute.getExtractor());
      exResult = ex.evaluate(document, XPathConstants.NODESET);
      
      NodeList nodes = (NodeList) exResult;
      if (nodes.getLength() > 1) {
        throw new DataExtractorException(String.format(
            "Configuration error. Configuration containes %s values for the specified key instead of 1",
            nodes.getLength()));
      }
      
      value = nodes.item(0).getNodeValue();
      
    } else {
      throw new DataExtractorException(
          "Attribute's extractor is not defined for the attribute "
              + attribute.toString());
    }
    
    return value;
  }
  
}
