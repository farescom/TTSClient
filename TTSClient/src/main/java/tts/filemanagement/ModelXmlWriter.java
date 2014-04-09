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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

import tts.model.SoundFile;
import xml.AbstractXmlWriter;


public class ModelXmlWriter extends AbstractXmlWriter<ArrayList<SoundFile>>
{
   public ModelXmlWriter() throws ParserConfigurationException,
         TransformerException
   {
      super();
   }

   public Element createXmlFromModel(ArrayList<SoundFile> soundFiles)
   {
      Element root = super.createElement("SoundList");
      for (int i=0; i<soundFiles.size(); i++)
      {  
          Element soundUnit = super.createElement("Sound",
                  new String[]{"Text", "File"},
                  new String[]{soundFiles.get(i).getText(), soundFiles.get(i).getFile()});
          root.appendChild(soundUnit);
      }
      return root;
   }
}