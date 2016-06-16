package View;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuTypeJeu extends BasicGameState {

	public static final int ID = 2;
	private Image background;
	
	private StateBasedGame game;

	@Override
	public void init(GameContainer container, StateBasedGame game)
		throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		 background.draw(0, 0, container.getWidth(), container.getHeight());
		 g.drawString("Appuyer sur une touche deuxième fois haah", 300, 300);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public void keyReleased(int key, char c) {
		System.out.println("bounjour");
	    game.enterState(WindowGame.ID);
	  }

	

}
