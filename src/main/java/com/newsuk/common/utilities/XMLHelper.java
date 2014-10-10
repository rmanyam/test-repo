package com.newsuk.common.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class XMLHelper 
{
	public Document getDocument(String documentPath)
	{
//		System.out.println("documentPath change start is :"+documentPath);
//		documentPath = "/C:/Workspace/TestAutomationWeb2/TTOAutomation/target/classes/data/OptaEvents/teams.xml";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		
//		InputStream ip = null;
//		try {
//			ip = getClass().getClassLoader().getResourceAsStream(documentPath);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		
		try {
			builder = factory.newDocumentBuilder();
			//System.out.println("documentPath 5");
			doc = builder.parse(documentPath);			
			//doc = builder.parse(ip);
			//System.out.println("documentPath 6 :"+doc);
		} catch (ParserConfigurationException e){
			System.err.println("Unable to parse the XML file");			
		} catch (IOException e) {
			System.err.println("Unable to read the file from the content path")	;			
		} catch (SAXException e) {			 
			System.err.println("Unable to parse the XML file");	
			e.printStackTrace();
		} catch (Exception e) {			 
			System.err.println("Exception");	
			e.printStackTrace();
		} 

		return doc;
	}

	public void editXMLNode(String xpathString, String sourceFile, String resultFile,String replaceText) throws URISyntaxException, IOException
	{
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		Document sourceDoc = getDocument(getContentPath(sourceFile));

		try {
			XPathExpression expr =  xpath.compile(xpathString);
			Node node = (Node) expr.evaluate(sourceDoc, XPathConstants.NODE);
			node.setTextContent(replaceText);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(sourceDoc);

			resultFile = getContentPath(resultFile);
			File resFile = new File(resultFile);
			resFile.createNewFile();

			StreamResult result = new StreamResult(resFile);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		} catch (XPathExpressionException  exception)
		{
			exception.printStackTrace();

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

	}

	public String editXMLNode(String xpathString, String sourceFile, String replaceText) throws URISyntaxException, IOException
	{
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

//		System.out.println("sourcefile1 is :"+sourceFile);
//		System.out.println("content path is:"+getContentPath(sourceFile));
//		String path = "/C:/Workspace/TestAutomationWeb2/TTOAutomation/target/classes/data/OptaEvents/teams.xml";
		//Document sourceDoc = getDocument(path);
		Document sourceDoc = getDocument(getContentPath(sourceFile));
		
		//System.out.println("sourceDoc is:"+sourceDoc);
		//Document sourceDoc = getDocument(sourceFile);

		String result = null;

		try {
			XPathExpression expr =  xpath.compile(xpathString);
			Node node = (Node) expr.evaluate(sourceDoc, XPathConstants.NODE);
			node.setTextContent(replaceText);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(sourceDoc);

			ByteArrayOutputStream boa = new ByteArrayOutputStream();

			StreamResult resultStream = new StreamResult(boa);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, resultStream);

			result = boa.toString();

		} catch (XPathExpressionException  exception)
		{
			exception.printStackTrace();

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return result;

	}

//	public String editXMLNode(String sourceFilePath, List<String> xpathString, List<String> replaceText) throws URISyntaxException, IOException
//	{
//		XPathFactory xpathFactory = XPathFactory.newInstance();
//		XPath xpath = xpathFactory.newXPath();
//
//		Document sourceDoc = getDocument(getContentPath(sourceFilePath));
//
//		String result = null;
//
//		try {
//			for(int i=0; i< xpathString.size(); i++){
//
//				try{
//					System.out.println("xpath is: " + xpathString.get(i));
//					XPathExpression expr =  xpath.compile(xpathString.get(i));
//					Node node = (Node) expr.evaluate(sourceDoc, XPathConstants.NODE);
//					node.setTextContent(replaceText.get(i));
//
//
//					TransformerFactory transformerFactory = TransformerFactory.newInstance();
//					Transformer transformer = transformerFactory.newTransformer();
//
//					DOMSource source = new DOMSource(sourceDoc);
//
//					ByteArrayOutputStream boa = new ByteArrayOutputStream();
//
//					StreamResult resultStream = new StreamResult(boa);
//					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//					transformer.transform(source, resultStream);
//
//					result = boa.toString();
//				}
//				catch(NullPointerException e){
//					System.err.println("Unable to find xpath");
//					e.printStackTrace();
//				}
//			}
//
//		} 
//		catch (XPathExpressionException  exception)
//		{
//			exception.printStackTrace();
//		}
//		catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		return result;
//
//	}

	public String editXMLNode(String sourceFilePath, Map<String, String> editMap) throws URISyntaxException, IOException
	{
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		Document sourceDoc = getDocument(getContentPath(sourceFilePath));

		String result = null;
	
		try {
			
			for(Map.Entry<String, String> entry : editMap.entrySet()){
				
				try{
					System.out.println("xpath key is: " + entry.getKey());
					
					System.out.println("xpath value is: " + entry.getValue());
					XPathExpression expr =  xpath.compile((String) entry.getKey());
					Node node = (Node) expr.evaluate(sourceDoc, XPathConstants.NODE);
					node.setTextContent((String) entry.getValue());
	
	
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
	
					DOMSource source = new DOMSource(sourceDoc);
	
					ByteArrayOutputStream boa = new ByteArrayOutputStream();
	
					StreamResult resultStream = new StreamResult(boa);
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.transform(source, resultStream);
	
					result = boa.toString();
				}
				catch(NullPointerException e){
					System.err.println("Unable to find xpath");
					e.printStackTrace();
				}
			}
//			for(int i=0; i< xpathString.size(); i++){
//
//				try{
//					System.out.println("xpath is: " + xpathString.get(i));
//					XPathExpression expr =  xpath.compile(xpathString.get(i));
//					Node node = (Node) expr.evaluate(sourceDoc, XPathConstants.NODE);
//					node.setTextContent(replaceText.get(i));
//
//
//					TransformerFactory transformerFactory = TransformerFactory.newInstance();
//					Transformer transformer = transformerFactory.newTransformer();
//
//					DOMSource source = new DOMSource(sourceDoc);
//
//					ByteArrayOutputStream boa = new ByteArrayOutputStream();
//
//					StreamResult resultStream = new StreamResult(boa);
//					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//					transformer.transform(source, resultStream);
//
//					result = boa.toString();
//				}
//				catch(NullPointerException e){
//					System.err.println("Unable to find xpath");
//					e.printStackTrace();
//				}
//			}

		} 
		catch (XPathExpressionException  exception)
		{
			exception.printStackTrace();
		}
		catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;

	}

	
	private String getContentPath(String filePath)
	{	
		URL urlPath = getClass().getClassLoader().getResource(filePath);
		String contentPath = urlPath.getPath();
		return contentPath;
	}

	public String getUTCTime()
	{
		String DATEFORMAT = "yyyyMMdd'T'HHmmss'+0100'";
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String utcTime = sdf.format(new Date());
		return utcTime;
	}

	public String getUUID()
	{
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString();
	}
}
















