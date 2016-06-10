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
		case "Planter" :return Action.PLANT;
		case "Arroser" : return Action.WATER;
		case "Deposer" : return Action.DROP;
		case "Ramasser" : return Action.PICK;
		}
		
		return Action.MOVE; 
	}
	

	public Condition toCondition(Node Ncondi){
		Condition c1,c2;
		String s;
		String[] s1,s2;
		
		switch(Ncondi.getTextContent()){
		case "Et": 
			c1= toCondition(Ncondi.getNextSibling());
			c2= toCondition(Ncondi.getNextSibling().getNextSibling());
			return (new Et(c1,c2));
			break;
		case "Ou":
			c1= toCondition(Ncondi.getNextSibling());
			c2= toCondition(Ncondi.getNextSibling().getNextSibling());
			return (new Ou(c1,c2));
			;break;
		
		default:
			s=Ncondi.getFirstChild().getTextContent();
			s=s.substring(0, s.length());
			s1=s.split("(", 2);
			s2=s1[1].split(",",2);
			switch (s1[0]){
			case "Present":
				switch (s2[0]){
				case "Zombie": return new Presence(s2[1].charAt(0),"Zombie"); break;
				case "Ennemi": return new Presence(s2[1].charAt(0),"Ennemi"); break;
				case "Katana": return new Presence(s2[1].charAt(0),Decor.KATANA); break;
				case "Batte_baseball": return new Presence(s2[1].charAt(0),Decor.BASEBALL_BAT); break;
				case "Lapin": return new Presence(s2[1].charAt(0),Decor.RABBIT); break;
				case "Pomme": return new Presence(s2[1].charAt(0),Decor.APPLE); break;
				case "Pousse": return new Presence(s2[1].charAt(0),Decor.SPROUT); break;
				case "Herbe": return new Presence(s2[1].charAt(0),Decor.GRASS); break;
				case "Arbre": return new Presence(s2[1].charAt(0),Decor.TREE); break;
				case "Rocher": return new Presence(s2[1].charAt(0),Decor.ROCK); break;
				}
				break;
			case "ScanLoin": 
				switch (s2[0]){
				case "Zombie": return new ScanLoin(s2[1].charAt(0),"Zombie"); break;
				case "Ennemi": return new ScanLoin(s2[1].charAt(0),"Ennemi"); break;
				case "Katana": return new ScanLoin(s2[1].charAt(0),Decor.KATANA); break;
				case "Batte_baseball": return new ScanLoin(s2[1].charAt(0),Decor.BASEBALL_BAT); break;
				case "Lapin": return new ScanLoin(s2[1].charAt(0),Decor.RABBIT); break;
				case "Pomme": return new ScanLoin(s2[1].charAt(0),Decor.APPLE); break;
				case "Pousse": return new ScanLoin(s2[1].charAt(0),Decor.SPROUT); break;
				case "Herbe": return new ScanLoin(s2[1].charAt(0),Decor.GRASS); break;
				case "Arbre": return new ScanLoin(s2[1].charAt(0),Decor.TREE); break;
				case "Rocher": return new ScanLoin(s2[1].charAt(0),Decor.ROCK); break;
				}
				break;
			case "ScanProche": 
				switch (s2[0]){
				case "Zombie": return new ScanProche(s2[1].charAt(0),"Zombie"); break;
				case "Ennemi": return new ScanProche(s2[1].charAt(0),"Ennemi"); break;
				case "Katana": return new ScanProche(s2[1].charAt(0),Decor.KATANA); break;
				case "Batte_baseball": return new ScanProche(s2[1].charAt(0),Decor.BASEBALL_BAT); break;
				case "Lapin": return new ScanProche(s2[1].charAt(0),Decor.RABBIT); break;
				case "Pomme": return new ScanProche(s2[1].charAt(0),Decor.APPLE); break;
				case "Pousse": return new ScanProche(s2[1].charAt(0),Decor.SPROUT); break;
				case "Herbe": return new ScanProche(s2[1].charAt(0),Decor.GRASS); break;
				case "Arbre": return new ScanProche(s2[1].charAt(0),Decor.TREE); break;
				case "Rocher": return new ScanProche(s2[1].charAt(0),Decor.ROCK); break;
				}
				break;
			case "Case_alliee":
				switch (s2[0]){
				case "Zombie": return new Linked_cell(s2[1].charAt(0),"Zombie"); break;
				case "Ennemi": return new Linked_cell(s2[1].charAt(0),"Ennemi"); break;
				case "Katana": return new Linked_cell(s2[1].charAt(0),Decor.KATANA); break;
				case "Batte_baseball": return new Linked_cell(s2[1].charAt(0),Decor.BASEBALL_BAT); break;
				case "Lapin": return new Linked_cell(s2[1].charAt(0),Decor.RABBIT); break;
				case "Pomme": return new Linked_cell(s2[1].charAt(0),Decor.APPLE); break;
				case "Pousse": return new Linked_cell(s2[1].charAt(0),Decor.SPROUT); break;
				case "Herbe": return new Linked_cell(s2[1].charAt(0),Decor.GRASS); break;
				case "Arbre": return new Linked_cell(s2[1].charAt(0),Decor.TREE); break;
				case "Rocher": return new Linked_cell(s2[1].charAt(0),Decor.ROCK); break;
				}
				break;
			case "Case_ennemie": ;break;
			case "Case_neutre": ;break;
			}
		}
		
		return ;
	}

	
	//constructeur
	XMLReader(){}
	
	//renvoi le nombre d'automate 
	ArrayList<ArrayList<transfer>> read (){ // file name 
		 
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
				document = builder.parse(new File("/home/zennouche/Documents/semestre6/PLA/exemple.xml"));
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

		 
//******************** début de la lecture ****************************\\
		
		int etat_courant; 
		int etat_futur;
		Action action; 
		Condition condition; 
		char direction ;
		int nbTransition = 0 ; 
		int NumEtatsMax ;
		int priority ; 
		Node NoeudCourant; 
		Node NoeudCondi;
		NodeList NListtransi ; 
		transfer cell; 
		ArrayList<ArrayList<transfer>> L = new ArrayList<ArrayList<transfer>>(); 
		ArrayList<transfer> L2 =null; 
		

		//recup la liste des autaomes.
		NodeList Nautomate = racine.getChildNodes();
		
		int nbNoeudsAutomate = Nautomate.getLength();
		 
		// Se place sur le premier noueds automate.
	//    nodeAuto = racine.getFirstChild(); 
		
			
		//System.out.println(Integer.toString(nbNoeuds));
		
		// parcour la liste des transition.
		for (int i = 0; i<nbNoeudsAutomate; i++) {

		    System.out.println(Nautomate.item(i).getNodeName());
		    

		    //récupère la liste des Noeuds transition.
		    NListtransi = Nautomate.item(i).getChildNodes();

		    //récup le nombre de transition de cet automate.
		    nbTransition =  NListtransi.getLength() ;
		    		
		    L2= new ArrayList<transfer>();
		    
		    for (int j = 0; j < nbTransition ; j++ ){
		    
		    	System.out.println(NListtransi.item(j).getNodeName());
		    
		    	//récupe de l'état courant 
		    	NoeudCourant = NListtransi.item(j).getFirstChild();
		    	System.out.println(NoeudCourant.getTextContent());
		    	etat_courant = Integer.parseInt(NoeudCourant.getTextContent());
		    		    	
		    	//récup condition
		    	
		    	NoeudCourant = NoeudCourant.getNextSibling();
		    	NoeudCondi = NoeudCourant.getFirstChild();
		    	System.out.println(NoeudCondi.getTextContent());
		    	condition = NoeudCourant.getTextContent() ;

		    	//récupération de l'action
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	action = toAction(NoeudCourant.getTextContent()) ;
		    	
		    	//recup de la direction 
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	direction = NoeudCourant.getTextContent().charAt(0) ;
		    	
		    	//recup de la priorité
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
		    	System.out.println(NoeudCourant.getTextContent());
		    	priority = Integer.parseInt(NoeudCourant.getTextContent()) ;
		    	
		    	//recup de l'état futur
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
