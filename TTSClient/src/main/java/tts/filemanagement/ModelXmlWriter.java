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