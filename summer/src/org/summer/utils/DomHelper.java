package org.summer.utils;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DomHelper {

    public static Document readXml(String path) throws DocumentException {
        SAXReader sr = new SAXReader();
        return sr.read(new File(path));
    }

    public static Element getElement(Document doc, String path) {
        return (Element) doc.selectSingleNode(path);
    }

    public static String getAttribute(Element ele, String name) {
        return ele.attributeValue(name);
    }

}
