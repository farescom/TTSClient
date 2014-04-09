/*
 * Copyright (C) 2014 www.LearnProgrammingNow.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU LESSER GENERAL PUBLIC LICENSE as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU LESSER GENERAL PUBLIC LICENSE for more details.
 *
 * You should have received a copy of the GNU LESSER GENERAL PUBLIC LICENSE
 * along with this program.  If not, see <http://www.gnu.org/licenses/lgpl-3.0.txt>.
 */
package xml;

import java.io.StringWriter;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class AbstractXmlWriter<Model> implements IXmlWriter<Model>
{
   private Document doc;

   public AbstractXmlWriter() throws ParserConfigurationException, TransformerException
   {
      doc = createDocument();
   }

   @Override
   public void toXml(Model model, String filename) throws TransformerException,
         ParserConfigurationException
   {
      Element rootElement = createXmlFromModel(model);

      while (doc.hasChildNodes())
         doc.removeChild(doc.getFirstChild());

      doc.appendChild(rootElement);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);

      if (!filename.toLowerCase().endsWith(".xml"))
         filename += ".xml";
      File file = new File(filename);
      StreamResult resultForFile = new StreamResult(file);
      transformer.transform(source, resultForFile);
      //System.out.println("New file created: " + file.getAbsolutePath());
   }

   @Override
   public String toXml(Model model, boolean includeHeader)
         throws TransformerException, ParserConfigurationException
   {
      Element rootElement = createXmlFromModel(model);

      while (doc.hasChildNodes())
         doc.removeChild(doc.getFirstChild());

      doc.appendChild(rootElement);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      if (!includeHeader)
         transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);

      StringWriter sw = new StringWriter();
      StreamResult resultForStrings = new StreamResult(sw);
      transformer.transform(source, resultForStrings);
      String xmlString = sw.toString();

      return xmlString;
   }

   public Element createElement(String tagName, String[] subTagNames,
         Object[] values)
   {
      Element element = doc.createElement(tagName);

      for (int i = 0; i < subTagNames.length; i++)
      {
         if (values[i] != null)
         {
            if (values[i] instanceof Element)
            {
               element.appendChild((Element) values[i]);
            }
            else
            {
               Element subElement = doc.createElement(subTagNames[i]);
               subElement.appendChild(doc.createTextNode("" + values[i]));
               element.appendChild(subElement);
            }
         }
      }
      return element;
   }

   public Element createElement(String tagName, Object value)
   {
      Element element = doc.createElement(tagName);
      element.appendChild(doc.createTextNode("" + value));
      return element;
   }

   public Element createElement(String tagName)
   {
      Element element = doc.createElement(tagName);
      return element;
   }

   private Document createDocument() throws ParserConfigurationException,
         TransformerException
   {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      return docBuilder.newDocument();
   }

}
