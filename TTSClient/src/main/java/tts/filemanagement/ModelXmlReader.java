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
package tts.filemanagement;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import tts.model.SoundFile;


public class ModelXmlReader extends DefaultHandler
{
    String value;
    ArrayList<SoundFile> soundFiles;
    String text;
    String file;
    SoundFile soundFile;

   public ArrayList<SoundFile> getModelFromFile(String filename)
         throws ParserConfigurationException, SAXException, IOException
   {
      SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
      parser.parse(new File(filename), this);

      return soundFiles;
   }
   
   public ArrayList<SoundFile> getModelFromString(String xmlString)
         throws ParserConfigurationException, SAXException, IOException
   {
      SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
      InputSource iSource = new InputSource( new StringReader( xmlString ) );
      parser.parse( iSource, this );

      return soundFiles;
   }

   public void startElement(String uri, String localName, String qName,
         Attributes attributes) throws SAXException
   {
      value = "";
      switch (qName)
      {
         case "SoundList":
             soundFiles = new ArrayList<SoundFile>();
             break;
      }
   }

   public void endElement(String uri, String localName, String qName)
         throws SAXException
   {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
      switch (qName)
      {
         case "Text":
            text = value;
            break;
         case "File":
            file = value;
            break;
         case "Sound":
            soundFile = new SoundFile(text, file);
            soundFiles.add(soundFile);
            break;
      }
   }

   public void characters(char ch[], int start, int length) throws SAXException
   {
      value += new String(ch, start, length);
   }
   
}