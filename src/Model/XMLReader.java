package Model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
//import org.w3c.dom.xpath.XPathExpression;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.w3c.dom.*;

public class XMLReader {
	
	public Action toAction(String act){
		
		
		switch(act){
		case "Attaquer" : return  Action.ATTACK ;
		case "Deplacer" : return (Action.MOVE) ;
		case "Voler" : return Action.STEAL;
		case "Echanger" :return Action.SWAP;
		//SWAP,
		case "Planter" :return Action.PLANT;
		case "Arroser" : return Action.WATER;
		case "Deposer" : return Action.DROP;
		case "Ramasser" : return Action.PICK;
		}
		
		return Action.MOVE; 
	}
	

	
	
	
	//constructeur
	XMLReader(){}
	
	//renvoi le nombre d'automate , path est l'endoirt ou est le fichier.
	ArrayList<ArrayList<transfer>> read (String path){ // file name 
		 
		System.out.println("bonjour");
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		
//*********** initialisation du parser **************************\\
		    DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}       
		    Document document = null;
			try {
				document = builder.parse(new File(path));
				document.getDocumentElement().normalize();
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	//racine !! :
			
			final Node racine = document.getDocumentElement();
			
//*************enl�ve les espace ***************************\\
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

		 
//******************** d�but de la lecture ****************************\\
		
		int etat_courant; 
		int etat_futur;
		Action action; 
		Condition condition; 
		char direction ;
		int nbTransition = 0 ; 
		int NumEtatsMax ;
		int priority ; 
		Node NoeudCourant; 	
		NodeList NListtransi ; 
		transfer cell; 
		ArrayList<ArrayList<transfer>> L = new ArrayList<ArrayList<transfer>>(); 
		ArrayList<transfer> L2 =null; 
		

		//recup la liste des autaomes.
		NodeList Nautomate = racine.getChildNodes();
		
		int nbNoeudsAutomate = Nautomate.getLength();
		 
		// Ce place sur le premier noueds automate.
	//    nodeAuto = racine.getFirstChild(); 
		
			
		//System.out.println(Integer.toString(nbNoeuds));
		
		// parcour la liste des transition.
		for (int i = 0; i<nbNoeudsAutomate; i++) {

		    System.out.println(Nautomate.item(i).getNodeName());
		    

		    //r�cup�re la liste des Noueds transition.
		    NListtransi = Nautomate.item(i).getChildNodes();

		    //r�ucp le nombre de transition de cet automate.
		    nbTransition =  NListtransi.getLength() ;
		    		
		    L2= new ArrayList<transfer>();
		    
		    for (int j = 0; j < nbTransition ; j++ ){
		    
		    	System.out.println(NListtransi.item(j).getNodeName());
		    
		    	//r�cupe de l'�tat courant 
		    	NoeudCourant = NListtransi.item(j).getFirstChild();
		    	System.out.println(NoeudCourant.getTextContent());
		    	etat_courant = Integer.parseInt(NoeudCourant.getTextContent());
		    		    	
		    	//recup condition
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	condition = NoeudCourant.getTextContent() ;

		    	//recup�ration de l'acion
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	action = toAction(NoeudCourant.getTextContent()) ;
		    	
		    	//recup de la direction 
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	direction = NoeudCourant.getTextContent().charAt(0) ;
		    	
		    	//recup de la priorit� 
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	priority = Integer.parseInt(NoeudCourant.getTextContent()) ;
		    	
		    	//recup de l'�tat futur
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	etat_futur =Integer.parseInt( NoeudCourant.getTextContent());
		    	
		    	cell = new transfer(etat_courant, condition, action, direction, priority, etat_futur);
		    	L2.add(cell);
		    }
		    L.add(L2);
		// nodeAuto = nodeAuto.getNextSibling(); 
		 
		}

		
		
		// Player. add(automate) 
		
		return L ;
	}
}
