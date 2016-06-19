package View;

import java.io.File;

import java.io.IOException;
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

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.released = false ;
		this.background = LoadingScreen.valider;
		this.oui = LoadingScreen.oui ;
		oui.setImageColor(0.30f, 0.20f, 0.30f);
		this.non= LoadingScreen.non;
		non.setImageColor(0.30f, 0.20f, 0.30f);
		this.souris = LoadingScreen.souris; 
		this.souris2 = LoadingScreen.souris2;
		
//		System.out.println("\n\n\nenter\n\n\n");
		
		
		
		container.setMouseCursor(this.souris, 0, 0);
		StateGame.jeu(mode);// ouvrir le gedit
		this.released = false ;
		super.enter(container, game);
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.mode = 2 ; 
		this.released = false ;
		
//		container.setMouseCursor(this.souris, 0, 0);
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
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		
			System.out.println("\n\n\nleave\n\n\n");
	
			try {
				Runtime.getRuntime().exec("make", null, new File("../Zombautomate/ocaml/")) ;
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(700);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				Runtime.getRuntime().exec("../Zombautomate/ocaml/xml_writter");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	
			
			ArrayList<Character> lC = StateGame.loadCharacters(mode) ; 
			Map carte;
			
			if(mode<0)
			{
				if(mode == -4) carte = Moteur.initiate_demo_map_2(lC);
				else if(mode == -5) carte = Moteur.initiate_demo_map_3(lC);
				else carte = Moteur.initiate_demo_map(lC, StateGame.getZombies());
			}
			else
			{
				carte = Moteur.initiate_map(lC, StateGame.getZombies());
			}
			
			WindowGame.ordo = new Ordonnanceur(lC);
			WindowGame.charactersList = lC;
			WindowGame.map = carte ; 
			
		super.leave(container, game);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int PosX = Mouse.getX() ;
		int PosY = Mouse.getY() ;
		int larg = container.getWidth();
		int haut = container.getHeight();
		
		if(!Mouse.isButtonDown(0)){
			this.released = true;
		}
		
		if (  PosX> larg/8 + larg/20 && PosX<larg/8 + larg/3 -larg/20 && (PosY <haut -  haut/4 - haut/20 ) && (PosY > haut - (haut/4 +haut/3) +haut/20)){
			container.setMouseCursor(this.souris2, 0, 0);
			this.oui= new Image ("../Zombautomate/ressources/Menu/panneau-yes.png");

			if(released  && Mouse.isButtonDown(0)){
				
				if (mode < 0 ){
					System.out.println("je quite vers windowsgame");
					
					game.enterState(WindowGame.ID);
				}
				else
				game.enterState(Credit.ID);
			}
		}
		else  oui.setImageColor(0.30f, 0.20f, 0.30f);

				
		
		if (PosX > larg/8 + larg/5 + larg/20 && PosX < larg/8 + larg/5 + larg/3 - larg/20 && PosY < haut - haut/2 - haut/20 && PosY> haut -(haut/2 +haut/3)+ haut/20){
			container.setMouseCursor(this.souris2, 0, 0);
			this.non= new Image ("../Zombautomate/ressources/Menu/panneau-no.png");

			if(released  && Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}  else  non.setImageColor(0.30f, 0.20f, 0.30f);
	}



	@Override
	public int getID() {
		return ID;
	}

}
