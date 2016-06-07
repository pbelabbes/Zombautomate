package Model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.xpath.XPathExpression;

import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

public class XMLReader {
	
	XMLReader(){}
	
	//renvoi le nombre d'automate 
	int read (){//Player p, String filename ){
		int nbautomate = 1 ; 
		System.out.println("bonjour");
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		

		    DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}       
		    Document document = null;
			try {
				document = builder.parse(new File("D:\\travail\\java\\Zombautomate\\exemple.xml"));
				document.getDocumentElement().normalize();
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			final Node racine = document.getDocumentElement();
			
			XPathFactory xpathFactory = XPathFactory.newInstance();
			// XPath to find empty text nodes.
			javax.xml.xpath.XPathExpression xpathExp = null;
			try {
				xpathExp =  xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			NodeList emptyTextNodes = null;
			try {
				emptyTextNodes = (NodeList) 
				        xpathExp.evaluate(racine, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Remove each empty text node from document.
			for (int i = 0; i < emptyTextNodes.getLength(); i++) {
			    Node emptyTextNode = emptyTextNodes.item(i);
			    emptyTextNode.getParentNode().removeChild(emptyTextNode);
			}

		 
		
		
		//Node node = racine.getFirstChild(); 
		//nextN = nextN.getFirstChild(); 
		
		NodeList racineNoeuds = racine.getChildNodes();
		
		final int nbRacineNoeuds = racineNoeuds.getLength();
		
		System.out.println(Integer.toString(nbRacineNoeuds));
		
		for (int i = 0; i<nbRacineNoeuds; i++) {
			System.out.println("i = "+  Integer.toString(i));
		    System.out.println(racineNoeuds.item(i).getNodeName());
		}

		
		
		// Player. add(autamate) 
		
		return nbautomate ;
	}
}
