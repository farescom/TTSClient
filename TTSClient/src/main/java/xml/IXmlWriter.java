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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;

public interface IXmlWriter<Model>
{
   public Element createXmlFromModel(Model model);

   public void toXml(Model model, String filename) throws TransformerException,
         ParserConfigurationException;

   public String toXml(Model model, boolean includeHeader)
         throws TransformerException, ParserConfigurationException;
}