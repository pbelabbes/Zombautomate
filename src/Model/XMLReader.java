package Model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

public class XMLReader {
	
	XMLReader(){}
	
	//renvoi le nombre d'automate 
	int read (){//Player p, String filename ){
		int nbautomate = 1 ; 
		
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
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 
		final Element racine = document.getDocumentElement();
		
		final NodeList racineNoeuds = racine.getChildNodes();
		
		final int nbRacineNoeuds = racineNoeuds.getLength();
		
		for (int i = 0; i<nbRacineNoeuds; i++) {
		    System.out.println(racineNoeuds.item(i).getNodeName());
		}

		
		
		// Player. add(autamate) 
		
		return nbautomate ;
	}
}
