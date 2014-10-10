package com.newsuk.common.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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

public class XmlReaderHelper {
	
	private Document document;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private XPathFactory xPathfactory;
	private XPath xpath;
	
	public XmlReaderHelper(String xmlString){
		
		xPathfactory = XPathFactory.newInstance();
		xpath = xPathfactory.newXPath();
		byte[] bytes = null;

		try {
			bytes = xmlString.getBytes("UTF-8");
			InputStream input = new ByteArrayInputStream(bytes);
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(input);	
		}
		catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getValueAtPath(String xpathString){
		String value = null;
		try {
			XPathExpression expr = xpath.compile(xpathString);
			value = (String) expr.evaluate(document, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public int getNumberOfElements(String xpathString){
		
		NodeList nodeList;
		try{
		XPathExpression expr = xpath.compile(xpathString);
		nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		
		return nodeList.getLength();
		}
		catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem getting node list");
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getIndexOfNodeWithValue(String xpathToNodes, String searchValue){
		NodeList nodeList;
		try {
			XPathExpression expr = xpath.compile(xpathToNodes);
			nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
			
			System.out.println("node list size: " + nodeList.getLength());
			
			for (int i=0; i<nodeList.getLength(); i++){
				System.out.println("enter loop");
				System.out.println("nodelist value: " + nodeList.item(i).getTextContent());
				
				if(nodeList.item(i).getTextContent().equals(searchValue)){
					return i;
				}
			}
			
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem getting node value");
			e.printStackTrace();
		}
		return -1;
	}
	

}
