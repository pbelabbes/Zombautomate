package View;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//import Model.* ;
import Model.Character;
import Model.Map;
import Model.Moteur;
import Model.Player;
import Model.XMLReader;
import Model.transfer;

public class StateGame extends StateBasedGame {

	/**
	 * Cette fonction permet de lire le fichié du joueur ecrit en ocaml et genere le fichier
	 * XML correspondant et renvoie le nom du fihcier XML correspondant
	 * @param name
	 * @return
	 */
	public static String demandeautomate(String name){
		try {
			Runtime.getRuntime().exec("gedit "+name);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Runtime.getRuntime().exec("make", null, new File("../Zombautomate/ocaml/")) ;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Runtime.getRuntime().exec("../Zombautomate/ocaml/xml_writter");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*

	 * fonction qui lit les xml et revnoi la liste des charactere
	 * Mode 1 : 1 human vs Zombie
	 * Mode 2 : 2 humain vs Zombie
	 * Mode 3 : variante ...
	 * Mode 4 : coninue 1 human vs Zombie
	 * Mode 5 : continue  2 humain vs Zombie
	 */
	public static ArrayList<Character> jeu ( int mode ){
		ArrayList<Character>  lC = new ArrayList<Character>() ; 
		XMLReader fichier = new XMLReader() ;

		//String path = demandeautomate("../Zombautomate/ocaml/user1.ml");


		/*try {
			Runtime.getRuntime().exec(new String[]{"cat" ,"../Zombautomate/ocaml/equipe1.xml", ">>", "../Zombautomate/ocaml/test.xml" });
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		String fich2;
		String fich1 ;
		
		if(mode ==1|| mode==4){
			if(mode==4){
				try {
					Runtime.getRuntime().exec("rm "+"V1_user1");
				} catch (IOException e) {
					e.printStackTrace();
			}
			 fich1 =demandeautomate("V1_user1");
			
		}
		if(mode==2 || mode==5)
		{		
			
			 if(mode==5){
				 try {
						Runtime.getRuntime().exec("rm "+"V2_user2");
					} catch (IOException e) {
						e.printStackTrace();
					}
			 }
			    fich1 =demandeautomate("V2_user1");
				fich2=demandeautomate("V2_user2");
				ArrayList<ArrayList<transfer>> equipe2=fichier.read(fich2);	
				Player j2 = new Player(2 ,"Joueur 2", 10);
				j2.setEntities(Moteur.CreateEntities(j2,equipe2));
				lC.addAll(j2.getEntities());
		}
		ArrayList<ArrayList<transfer>> equipe1=fichier.read(fich1);
		
		Player j1 = new Player(1 ,"Joueur 1", 10);
		j1.setEntities(Moteur.CreateEntities(j1,equipe1));
		lC.addAll(j1.getEntities());
		
	}
	
		return lC;
	}

	


	

/**
 * Cette fonction permet d'initialiser le joueur du zombie
 * @return
 */
public static Player getZombies(){
	XMLReader fichier = new XMLReader() ;

	ArrayList<ArrayList<transfer>> equipezombie=fichier.read("../Zombautomate/ocaml/zombies.xml");
	Player j0= new Player(0,"Joueur 0",10);
	j0.setEntities(Moteur.CreateEntities(j0,equipezombie));

	return j0;
}

@Override
/**
 * 
 * @param arg0
 * @throws SlickException
 */
public void initStatesList(GameContainer arg0) throws SlickException {
	ArrayList<Character> lC = jeu (1) ; 
	Map carte = Moteur.initiate_map(lC, getZombies());
	addState(new MainScreenGameState());
	addState(new MenuTypeJeu()) ;
	addState(new ContinueMenutypeJeu());
	//	addState(new WindowGame(lC,carte) ) ;
}

public StateGame() {
	super("ZOMBAUTOMATE by PANDAS");
}

/**
 * @param args
 * @throws SlickException 
 */
//	public static void main(String[] args) throws SlickException {
//		new AppGameContainer(new StateGame(), 800, 600, false).start();
//
//	}
/**
 * 
 * @param args
 * @throws SlickException
 */
public static void main(String[] args) throws SlickException {
	//ArrayList<Character> lC = jeu (1) ;
	//WindowGame wg = new WindowGame(lC , Moteur.create_map(lC) ).init_map().setAutomate();
	AppGameContainer app= new AppGameContainer(new StateGame(), 1200, 730, false);
	// wg.setScreenDimension(app.getScreenWidth(), app.getScreenHeight());
	app.start();
}

}
