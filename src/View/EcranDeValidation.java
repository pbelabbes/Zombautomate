package View;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class EcranDeValidation extends BasicGameState implements GameState {
	public static final int ID = 4;
	private Image background;
	private Image oui;
	private Image non;
	public static int mode ;
	private boolean released ;
	private StateBasedGame game;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/valider.png");
		this.oui = new Image ("../Zombautomate/ressources/Menu/panneau-yes.png");
		this.non= new Image ("../Zombautomate/ressources/Menu/panneau-no.png");
		this.released = false ;

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
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int PosX = Mouse.getX() ;
		int PosY = Mouse.getY() ;
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		
		if(!Mouse.isButtonDown(0)){
			this.released = true;
		}
		
		if (PosX> larg/8 && PosX<larg/8 + larg/3 && (haut - 
				
			
	}

	@Override
	public int getID() {
		return ID;
	}

}
