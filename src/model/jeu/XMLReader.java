package model.jeu;
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

//import model.automate.*;
import model.conditions.*;
import model.decors.*;

import org.w3c.dom.*;
/**
 * La classe XMLReader est utilisée pour lire un fichier XML et créer les bonnes instance en fonction de celui-ci.
 * 
 */
public class XMLReader {
	
	/**
	 * La fonction toAction permet à partir du string du XML de renvoyer la bonne action de caseAutomate
	 * @param act le sring qui vient du fichier
	 * @return Action
	 */
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
	

	/**
	 * Cette fonction permet à partir du XML de créer les conditions de chaque caseAutomate
	 * @param Ncondi Le noeud correspondant au premier argument de la balise condition
	 * @return Condition :la condition
	 */
	public Condition toCondition(Node Ncondi){
		//System.out.println("Noeuud param�tre = " + Ncondi.getNodeName()) ;
		Condition c1,c2;
		String s;
		String[] s1,s2;
		s=Ncondi.getFirstChild().getFirstChild().getNodeValue();
//		s=Ncondi.getTextContent();
//		System.out.println(s);
		
//		System.out.println(s);
		
		switch(s){
		case "Et": 
			//System.out.println("noueud name sibling  = " + Ncondi.getFirstChild().getNextSibling().getNodeName()) ;
			c1= toCondition(Ncondi.getFirstChild().getNextSibling());
			
			//System.out.println("noueud name sibling 2  = " + Ncondi.getNextSibling().getNextSibling().getNodeName()) ;
			
			c2= toCondition(Ncondi.getFirstChild().getNextSibling().getNextSibling());
			
			
			
			return (new Et(c1,c2));
		case "Ou":
			//System.out.println("\nnoueud name sibling  = " + Ncondi.getNextSibling().getNodeName()) ;
			c1= toCondition(Ncondi.getFirstChild().getNextSibling());
			
			//System.out.println("\nnoueud name sibling 2  = " + Ncondi.getNextSibling().getNextSibling().getNodeName()) ;
			
			c2= toCondition(Ncondi.getFirstChild().getNextSibling().getNextSibling());
			
			
			return (new Ou(c1,c2));
		case "Defaut" : 
			return new Defaut() ;			
		default:
			s=s.substring(0, s.length());
			s1=s.split("\\(", 2);
			s2=s1[1].split(",",2);
			switch (s1[0]){
			case "Present":
				switch (s2[0]){
				case "Allie":  return new Presence(s2[1].charAt(0),"allie");
				case "Zombie": return new Presence(s2[1].charAt(0),"zombie");
				case "Ennemi": return new Presence(s2[1].charAt(0),"ennemi");
				case "Katana": return new Presence(s2[1].charAt(0),Decor.KATANA);
				case "Batte_baseball": return new Presence(s2[1].charAt(0),Decor.BASEBALL_BAT);
				case "Lapin": return new Presence(s2[1].charAt(0),Decor.RABBIT); 
				case "Pomme": return new Presence(s2[1].charAt(0),Decor.APPLE);
				case "Pousse": return new Presence(s2[1].charAt(0),Decor.SPROUT);
				case "Herbe": return new Presence(s2[1].charAt(0),Decor.GRASS);
				case "Arbre": return new Presence(s2[1].charAt(0),Decor.TREE);
				case "Rocher": return new Presence(s2[1].charAt(0),Decor.ROCK);
				default: System.out.println("error invalid argument Present");
				}
				break;
			case "ScanLoin": 
				switch (s2[0]){
				case "Allie":  return new ScanLoin("allie",s2[1].charAt(0));
				case "Zombie": return new ScanLoin("zombie",s2[1].charAt(0));
				case "Ennemi": return new ScanLoin("ennemi",s2[1].charAt(0));
				case "Katana": return new ScanLoin(Decor.KATANA,s2[1].charAt(0));
				case "Batte_baseball": return new ScanLoin(Decor.BASEBALL_BAT,s2[1].charAt(0));
				case "Lapin": return new ScanLoin(Decor.RABBIT,s2[1].charAt(0));
				case "Pomme": return new ScanLoin(Decor.APPLE,s2[1].charAt(0));
				case "Pousse": return new ScanLoin(Decor.SPROUT,s2[1].charAt(0));
				case "Herbe": return new ScanLoin(Decor.GRASS,s2[1].charAt(0));
				case "Arbre": return new ScanLoin(Decor.TREE,s2[1].charAt(0));
				case "Rocher": return new ScanLoin(Decor.ROCK,s2[1].charAt(0));
				default: System.out.println("error invalid argument Scanloin");
				}
				break;
			case "ScanProche": 
				switch (s2[0]){
				case "Zombie": return new ScanProche("zombie",s2[1].charAt(0));
				case "Allie": return new ScanProche("allie",s2[1].charAt(0));
				case "Ennemi": return new ScanProche("ennemi",s2[1].charAt(0));
				case "Katana": return new ScanProche(Decor.KATANA,s2[1].charAt(0));
				case "Batte_baseball": return new ScanProche(Decor.BASEBALL_BAT,s2[1].charAt(0));
				case "Lapin": return new ScanProche(Decor.RABBIT,s2[1].charAt(0));
				case "Pomme": return new ScanProche(Decor.APPLE,s2[1].charAt(0));
				case "Pousse": return new ScanProche(Decor.SPROUT,s2[1].charAt(0));
				case "Herbe": return new ScanProche(Decor.GRASS,s2[1].charAt(0));
				case "Arbre": return new ScanProche(Decor.TREE,s2[1].charAt(0));
				case "Rocher": return new ScanProche(Decor.ROCK,s2[1].charAt(0));
				default: System.out.println("error invalid argument ScanProche");
				}
				break;
			case "Case_alliee":
				return new Linked_cell(s2[0].charAt(0),'A');
			case "Case_ennemie": 
				return new Linked_cell(s2[0].charAt(0),'E');
			case "Case_neutre": 
				return new Linked_cell(s2[0].charAt(0),'N'); 
			default: System.out.println("error invalid argument condition : "+s1[0]);
			}
			return null;
		}
	}

	
	//constructeur
	public XMLReader(){}
	


	//renvoi le nombre d'automate 
	/**
	 * La fonction read lit entièrement le fichier XML et renvoie une liste de transfer 
	 * @return une liste de transfer 
	 */
	public ArrayList<ArrayList<transfer>> read (String path){ // file name 

		 
		System.out.println("bonjour");
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		
//*********** initialisation du parser **************************\\
		    DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				
				e1.printStackTrace();
			}       
		    Document document = null;
			try {
				document = builder.parse(new File(path));
				document.getDocumentElement().normalize();
			} catch (SAXException | IOException e) {
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
				e.printStackTrace();
			}  
			NodeList emptyTextNodes = null;
			try {
				emptyTextNodes = (NodeList) 
				        xpathExp.evaluate(racine, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}

			// Remove each empty text node from document.
			for (int i = 0; i < emptyTextNodes.getLength(); i++) {
			    Node emptyTextNode = emptyTextNodes.item(i);
			    emptyTextNode.getParentNode().removeChild(emptyTextNode);
			}

		 
//******************** début de la lecture ****************************\\
		
			System.out.println("debut de la lecture");
		int etat_courant; 
		int etat_futur;
		Action action; 
		Condition condition; 
		char direction ;
		int nbTransition = 0 ; 
		//int NumEtatsMax ;
		int priority ; 
		Node NoeudCourant; 
		Node NoeudCondi;
		NodeList NListtransi ; 
		transfer cell; 
		ArrayList<ArrayList<transfer>> L = new ArrayList<ArrayList<transfer>>(); 
		ArrayList<transfer> L2 =null; 
		

		//recup la liste des automates.
		NodeList Nautomate = racine.getChildNodes();
		
		int nbNoeudsAutomate = Nautomate.getLength();
		 
		// Se place sur le premier noeuds automate.
	//    nodeAuto = racine.getFirstChild(); 
		
			
		//System.out.println(Integer.toString(nbNoeuds));
		
		// parcours la liste des transitions.
		for (int i = 0; i<nbNoeudsAutomate; i++) {

//		    System.out.println(Nautomate.item(i).getNodeName());
		    

		    //récupère la liste des Noeuds transition.
		    NListtransi = Nautomate.item(i).getChildNodes();

		    //récup le nombre de transition de cet automate.
		    nbTransition =  NListtransi.getLength() ;
		    		
		    L2= new ArrayList<transfer>();
		    
		    for (int j = 0; j < nbTransition ; j++ ){
		    
//		    	System.out.println(NListtransi.item(j).getNodeName());
		    	
		    
		    	//récupérer l'état courant 
		    	NoeudCourant = NListtransi.item(j).getFirstChild();
//		    	System.out.println(NoeudCourant.getTextContent());
		    	etat_courant = Integer.parseInt(NoeudCourant.getTextContent());
		    		    	
		    	//récupérer la condition
		    	NoeudCourant = NoeudCourant.getNextSibling();
		    	//NoeudCondi = NoeudCourant.getFirstChild();
		    	NoeudCondi = NoeudCourant ;
//		    	System.out.println(NoeudCondi.getTextContent());
		    	condition = toCondition(NoeudCondi);
		    	

		    	//récupérer l'action
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
//		    	System.out.println(NoeudCourant.getTextContent());
		    	action = toAction(NoeudCourant.getTextContent()) ;
		    	
		    	//récupérer la direction 
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
//		    	System.out.println(NoeudCourant.getTextContent());
		    	direction = NoeudCourant.getTextContent().charAt(0) ;
		    	
		    	//récupérer la priorité
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
//		    	System.out.println(NoeudCourant.getTextContent());
		    	priority = Integer.parseInt(NoeudCourant.getTextContent()) ;
		    	
		    	//récupérerl'état futur
		    	NoeudCourant = NoeudCourant.getNextSibling(); 
//		    	System.out.println(NoeudCourant.getTextContent());
		    	etat_futur =Integer.parseInt( NoeudCourant.getTextContent());
		    	//transfer(int etat_courant,ArrayList<Condition> condition, Action action , char direction, int priority, int etat_futur) 
		    	cell = new transfer(etat_courant, condition, action, direction, priority, etat_futur);
		    	L2.add(cell);
		    }
		    L.add(L2);
		// nodeAuto = nodeAuto.getNextSibling(); 
		 
		}

		System.out.println("fin");
		
		// Player. add(automate) 
		
		return L ;
	}
}
