package View;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import sun.awt.windows.ThemeReader;

//import Model.* ;
import Model.Character;
import Model.Map;
import Model.Moteur;
import Model.Ordonnanceur;
import Model.Player;
import Model.XMLReader;
import Model.transfer;

public class StateGame extends StateBasedGame {

//cettte focntion permet de réinitialiser un fichier automate
public static void initiateboth(){
//	String tmp = Integer.toString(idj);
	
	try {
		Runtime.getRuntime().exec(new String[]{ "sh", "-c", "rm ../Zombautomate/ocaml/user1.ml"});
		Runtime.getRuntime().exec(new String[]{ "sh", "-c", "rm ../Zombautomate/ocaml/user2.ml"});
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	try{
		Process proc1=Runtime.getRuntime().exec(new String[]{ "sh", "-c", "cat ../Zombautomate/ocaml/exemple_fichier_user1.ml"} );
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		Process proc2=Runtime.getRuntime().exec(new String[]{ "sh", "-c", "cat ../Zombautomate/ocaml/exemple_fichier_user2.ml"} );
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		InputStream in1 = proc1.getInputStream();
		InputStream in2 = proc2.getInputStream();
		BufferedWriter out1= new BufferedWriter(new FileWriter("../Zombautomate/ocaml/user1.ml"));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		BufferedWriter out2= new BufferedWriter(new FileWriter("../Zombautomate/ocaml/user2.ml"));
		int c;
		while ((c = in1.read()) != -1) {

			out1.write((char)c);
		}
		while ((c = in2.read()) != -1) {

			out2.write((char)c);
		}		
		in1.close();
		out1.flush();
		out1.close();
		in2.close();
		out2.flush();
		out2.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
}	


	/**
	 * Cette fonction permet de lire le fichié du joueur ecrit en ocaml et genere le fichier
	 * XML correspondant et renvoie le nom du fihcier XML correspondant
	 * @param idj identifiant joueur
	 * @return
	 */
	public static void demandeautomate(int idj){

		String tmp = Integer.toString(idj);
		try {
			Runtime.getRuntime().exec("gedit ../Zombautomate/ocaml/user"+tmp+".ml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
	}
	
	//lance make puis execture 
	public static void compileAndRun(){
		System.out.println("\n\n\n je suis dans compile and run \n\n\n");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		try {
			Runtime.getRuntime().exec("make", null, new File("../Zombautomate/ocaml/")) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Runtime.getRuntime().exec("../Zombautomate/ocaml/xml_writter");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/*
	 * fonction qui lit les xml et revnoi la liste des charactere
	 * Mode 1 : 1 human vs Zombie
	 * Mode 2 : 2 humain vs Zombie
	 * Mode 3 : variante ...
	 * Mode 4 : coninue 1 human vs Zombie
	 * Mode 5 : continue  2 humain vs Zombie
	 */
	public static void jeu ( int mode ){
		
		if(mode ==1|| mode==4){
			if(mode==1){
				initiateboth() ;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}	
			}
			demandeautomate(1);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
		}
		if(mode==2 || mode==5)
		{		
			if(mode==2){
				initiateboth();
			}
			demandeautomate(1);	
			demandeautomate(2);
		}

}

	


	//modes positifs, => pour l'utilisateur
	//modes négatifs => démos

	public static ArrayList<Character> loadCharacters ( int mode ){
		ArrayList<Character>  lC = new ArrayList<Character>() ; 
		XMLReader fichier = new XMLReader() ;

		
		if(mode < 0)
		{
			ArrayList<ArrayList<transfer>> equipe1=fichier.read("demo"+(mode*-1)+".xml");//fich1);
			
			Player j1 = new Player(1,"Joueur 1",10);

			j1.setEntities(Moteur.CreateEntities(j1,equipe1));
			lC.addAll(j1.getEntities());
			return lC;

		}
		
		ArrayList<ArrayList<transfer>> equipe1=fichier.read("equipe1.xml");//fich1);

		Player j1 = new Player(1 ,"Joueur 1", 10);
		
		j1.setEntities(Moteur.CreateEntities(j1,equipe1));
		lC.addAll(j1.getEntities());
		Credit.J1 = j1 ;
		if(mode==2 || mode == 5)
		{		 
			ArrayList<ArrayList<transfer>> equipe2=fichier.read("equipe2.xml");//fich2);	
			Player j2 = new Player(2 ,"Joueur 2", 10);
			j2.setEntities(Moteur.CreateEntities(j2,equipe2));
			lC.addAll(j2.getEntities());
			Credit.J2 =j2; 
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


/**
 * 
 * @param arg0
 * @throws SlickException
 */
	public void initStatesList(GameContainer arg0) throws SlickException {
		
		if	((System.getProperties().get("os.name")).equals("Linux") ) {

			
		addState(new MainScreenGameState());
		addState(new MenuTypeJeu()) ;
		addState(new ContinueMenutypeJeu());
		addState(new EcranDeValidation());
		addState(new Credit());
		addState(new WindowGame() ) ;
		}
		
		else{
			ArrayList<Character> lC = StateGame.loadCharacters(2) ; 
			Map carte = Moteur.initiate_map(lC, StateGame.getZombies());
			Ordonnanceur ordo = new Ordonnanceur(lC);
			WindowGame wg = new WindowGame();
			wg.map =carte ; 
			wg.ordo = ordo ;
			wg.charactersList = lC ; 
			addState(wg);
		}
		
}

public StateGame() {
	super("ZOMBAUTOMATE by PANDAS");
}

/**
 * 
 * @param args
 * @throws SlickException
 */
public static void main(String[] args) throws SlickException {

//	ArrayList<Character> lC = jeu (1) ;

//	ArrayList<Character> lC = jeu (2) ;

	//WindowGame wg = new WindowGame(lC , Moteur.create_map(lC) ).init_map().setAutomate();

//	initiateboth();
//	compileAndRun();	
//	loadCharacters(1);
//	System.out.println("pru");
	
	
	
	AppGameContainer tmp = new AppGameContainer(null);
	AppGameContainer app= new AppGameContainer(new StateGame(),tmp.getScreenWidth(),tmp.getScreenHeight(),false);
	WindowGame.screenHeight= tmp.getScreenHeight();
	WindowGame.screenWidth= tmp.getScreenWidth();
	app.start();
	}

}
