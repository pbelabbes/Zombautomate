package View;

import java.util.ArrayList;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import model.jeu.*;
import model.jeu.Character;

public class EcranDeValidation extends BasicGameState implements GameState {
	public static final int ID = 4;
	private Image background;
	private Image oui;
	private Image non;
	private Image souris;
	private Image souris2 ; 
	public static int mode ;
	private boolean released ;
	private StateBasedGame game;
	private boolean launch;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/valider.png");
		this.oui = new Image ("../Zombautomate/ressources/Menu/panneau-yes.png");
		this.non= new Image ("../Zombautomate/ressources/Menu/panneau-no.png");
		this.souris = new Image ("../Zombautomate/ressources/Menu/UpArrow.png");
		this.souris2 = new Image ("../Zombautomate/ressources/Menu/AppStarting2.png");
		this.released = false ;
		container.setMouseCursor(this.souris, 0, 0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int larg = container.getWidth()  ;
		int haut = container.getHeight() ;
		background.draw(0, 0, container.getWidth(), container.getHeight());
		oui.draw(larg/8		,haut/4	,larg/3		,haut/3);
		non.draw(larg/8 + larg/ 5 , haut/2 , larg/3 , haut/3);
	
	}
	
	

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		container.setMouseCursor(this.souris, 0, 0);
		StateGame.jeu(mode);
		
		
		super.enter(container, game);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		
//			ArrayList<Character>  lC = new ArrayList<Character>() ; 
//			XMLReader fichier = new XMLReader() ;
			
			StateGame.compileAndRun();
			
			
//			if(mode == 2 || mode == 4){
//				ArrayList<ArrayList<transfer>> equipe2=fichier.read("../Zombautomate/ocaml/equipe2.xml");	
//				Player j2 = new Player(2 ,"Joueur 2", 10);
//				j2.setEntities(Moteur.CreateEntities(j2,equipe2));
//				lC.addAll(j2.getEntities());
//			}
//			
//			ArrayList<ArrayList<transfer>> equipe1=fichier.read("../Zombautomate/ocaml/equipe1.xml");
//			
//			Player j1 = new Player(1 ,"Joueur 1", 10);
//			j1.setEntities(Moteur.CreateEntities(j1,equipe1));
//			lC.addAll(j1.getEntities());
//		
//		}
			ArrayList<Character> lC = StateGame.loadCharacters(mode) ; 
			Map carte = Moteur.initiate_map(lC, StateGame.getZombies());
			Ordonnanceur ordo = new Ordonnanceur(lC);
			
			WindowGame wg = new WindowGame();
			wg.initialisedGameModel(lC, carte, ordo);
			AppGameContainer tmp1 = new AppGameContainer(null);
			AppGameContainer app1 = new AppGameContainer(wg,tmp1.getScreenWidth(),tmp1.getScreenHeight(),false);
			wg.setScreenDimension(tmp1.getScreenWidth(),tmp1.getScreenHeight());
			System.out.println(wg.screenWidth+"/"+tmp1.getScreenWidth()+" "+wg.screenHeight+"/"+app1.getScreenHeight());
			app1.start();
	
		
		super.leave(container, game);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int PosX = Mouse.getX() ;
		int PosY = Mouse.getY() ;
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		
		if(!Mouse.isButtonDown(0)){
			this.released = true;
		}
		
		if (  PosX> larg/8 + larg/20 && PosX<larg/8 + larg/3 -larg/20 && (PosY <haut -  haut/4 - haut/20 ) && (PosY > haut - (haut/4 +haut/3) +haut/20)){
			container.setMouseCursor(this.souris2, 0, 0);
			if(released  && Mouse.isButtonDown(0)){}
				this.launch = true ; 
//				System.exit(0);
			game.enterState(MainScreenGameState.ID);
//			game.enterState(WindowGame.ID);
		}
				
		
		if (PosX > larg/8 + larg/5 + larg/20 && PosX < larg/8 + larg/5 + larg/3 - larg/20 && PosY < haut - haut/2 - haut/20 && PosY> haut -(haut/2 +haut/3)+ haut/20){
			container.setMouseCursor(this.souris2, 0, 0);
			if(released  && Mouse.isButtonDown(0)){
				this.launch = false ; 
				System.exit(0);
			}
		}
	}



	@Override
	public int getID() {
		return ID;
	}

}
