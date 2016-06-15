package View;

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

	
	/*
	 * Mode 1 : 1 human vs Zombie
	 * Mode 2 : 2 humain v Zombie
	 * Mode 3 : variante ...
	 * 
	 */
	public static ArrayList<Character> jeu ( int mode ){
		ArrayList<Character>  lC = new ArrayList<Character>() ; 
		XMLReader fichier = new XMLReader() ;
		ArrayList<ArrayList<transfer>> liste1=fichier.read("../Zombautomate/ocaml/equipe1.xml");
		ArrayList<ArrayList<transfer>> liste2=fichier.read("../Zombautomate/ocaml/equipe2.xml");
		
		
		Player j1 = new Player(1 ,"Joueur 1", 10);
		Player j2 = new Player(2 ,"Joueur 2", 10);
		
		j1.setEntities(Moteur.CreateEntities(j1,liste1));
		j2.setEntities(Moteur.CreateEntities(j2,liste2));
		
		lC.addAll(j1.getEntities());
		lC.addAll(j2.getEntities());

		return lC;

	}
	
	
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		ArrayList<Character> lC = jeu (1) ; 
		Map carte = Moteur.create_map(lC);
		carte.init_map(); 
		addState(new MainScreenGameState());
		addState(new WindowGame(lC,carte) ) ;
	}
	
	public StateGame() {
	    super("ZOMBAUTOMATE V1");
	  }
	
	/**
	 * @param args
	 * @throws SlickException 
	 */
//	public static void main(String[] args) throws SlickException {
//		new AppGameContainer(new StateGame(), 800, 600, false).start();
//
//	}
	
	public static void main(String[] args) throws SlickException {
    	//ArrayList<Character> lC = jeu (1) ;
    	//WindowGame wg = new WindowGame(lC , Moteur.create_map(lC) ).init_map().setAutomate();
        AppGameContainer app= new AppGameContainer(new StateGame(), 800, 600, false);
       // wg.setScreenDimension(app.getScreenWidth(), app.getScreenHeight());
        app.start();
    }

}
