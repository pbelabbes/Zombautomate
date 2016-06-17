package View;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import Model.Character;

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
		ArrayList<Character> Lc  = StateGame.jeu(mode);
		
		WindowGame.perso = Lc ; 
		super.enter(container, game);
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

//			game.enterState(WindowGame.ID);
		}
				
		
		if (PosX > larg/8 + larg/5 + larg/20 && PosX < larg/8 + larg/5 + larg/3 - larg/20 && PosY < haut - haut/2 - haut/20 && PosY> haut -(haut/2 +haut/3)+ haut/20){
			container.setMouseCursor(this.souris2, 0, 0);
			if(released  && Mouse.isButtonDown(0)){
			System.exit(0);
			}
		}
	}



	@Override
	public int getID() {
		return ID;
	}

}
