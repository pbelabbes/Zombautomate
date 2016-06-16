package View;

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
	private boolean released  ;
	private StateBasedGame game;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/valider.png");
		this.oui = new Image ("../Zombautomate/ressources/Menu/oui.png");
		this.non= new Image ("../Zombautomate/ressources/Menu/non.png");
		this.released = false ;

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		background.draw(0, 0, container.getWidth(), container.getHeight());
		oui.draw(larg/2,haut/2,larg/5,haut/5);
//		non.draw();
		

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}

}
