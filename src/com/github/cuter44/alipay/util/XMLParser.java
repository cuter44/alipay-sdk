package com.github.cuter44.alipay.util;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Properties;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser
{
    private static class PropertyCollector extends DefaultHandler
    {
        private Properties prop = new Properties();
        private ArrayList<String> keyStack = new ArrayList<String>();
        private ArrayList<StringBuilder> valueStack = new ArrayList<StringBuilder>();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
        {
            this.keyStack.add(qName);
            this.valueStack.add(new StringBuilder());

            for (int i=keyStack.size()-2; i>=0; i--)
                valueStack.get(i)
                    .append('<')
                    .append(qName)
                    .append('>');

            return;
        }

        @Override
        public void endElement(String uri, String localName, String qName)
        {
            String key = this.keyStack.remove(keyStack.size()-1);
            String value = this.valueStack.remove(valueStack.size()-1).toString();

            this.prop.setProperty(key, value);

            for (int i=keyStack.size()-1; i>=0; i--)
                valueStack.get(i)
                    .append("</")
                    .append(qName)
                    .append('>');

            return;
        }

        @Override
        public void characters(char[] ch, int start, int length)
        {
            for (int i=keyStack.size()-1; i>=0; i--)
                valueStack.get(i)
                    .append(ch, start, length);

            return;
        }

        public Properties returnProperties()
        {
            return(this.prop);
        }
    }

    public static Properties parseXML(String xmlString)
    {
        if (xmlString==null)
            throw(new IllegalArgumentException("argument xmlString must not be null."));

        try
        {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            InputStream source = new ByteArrayInputStream(xmlString.getBytes("utf-8"));
            PropertyCollector pc = new PropertyCollector();

            parser.parse(source, pc);
            return(pc.returnProperties());
        }
        catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
        }
        catch (ParserConfigurationException ex)
        {
            ex.printStackTrace();
        }
        catch (SAXException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return(null);
    }

    public static void main(String[] args)
    {
        System.out.println("Hello World!");
    }
}
