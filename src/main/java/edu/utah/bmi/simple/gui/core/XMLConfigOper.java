package edu.utah.bmi.simple.gui.core;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Jianlin_Shi on 10/15/15.
 */
public class XMLConfigOper {
    public Document document;
    private String configFile;

    public XMLConfigOper(String configFile) {
        this.configFile=configFile;
        try {
            File inputFile = new File(configFile);
            SAXReader reader = new SAXReader();
            document = reader.read(inputFile);

//            System.out.println("Root element :"
//                    + document.getRootElement().getName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String expression) {
        String value = document.selectSingleNode(expression).getText().trim();
        return value;
    }

    public void setValue(String expression, String value) {
        Element element = (Element) document.selectSingleNode(expression);
        element.setText(value.trim());
    }

    public void setAttributeValue(String expression, String value) {
        Attribute element = (Attribute) document.selectSingleNode(expression);
        element.setText(value.trim());
    }

    public void addAttribute(String expression, String attr, String value) {
        Element element = (Element) document.selectSingleNode(expression);
        element.addAttribute(attr.trim(), value.trim());
    }

    public int getIntValue(String expression) {
        int output = -1;
        try {
            output = Integer.parseInt(getValue(expression));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public void save() {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer;
        try {
//            writer = new XMLWriter( System.out, format );
            writer = new XMLWriter(new FileWriter(new File(configFile)), format);
            writer.write(document);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
