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
	private Image hu1;
	private Image hu2;
	private Image var;
	private StateBasedGame game;
	
	
	@Override
	/**
	 * 
	 * @param arg0
	 * @param game
	 * @throws SlickException
	 */
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		this.hu1 = new Image ("../Zombautomate/ressources/Menu/1vZombie.png");
		this.hu2= new Image ("../Zombautomate/ressources/Menu/2vZombies.png");
		this.var = new Image ("../Zombautomate/ressources/Menu/variante.png");

	}

	@Override
	/**
	 * 
	 * @param container
	 * @param game
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		background.draw(0, 0, container.getWidth(), container.getHeight());
		hu1.draw(larg/2 - larg/10 ,    haut/2 - haut/30 -haut/18 					, larg/5, haut/7);
		hu2.draw(larg/2 - larg/10 ,   haut/2 + haut/7 - haut/18 					, larg/5, haut/7);
		var.draw(larg/2 - larg/10 ,     haut/2 + haut/7 + haut/7 + haut/30 - haut/18 , larg/5, haut/7);

	}

	@Override
	/**
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 * @throws SlickException
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}
	
	public void keyReleased(int key, char c) {
		System.out.println("bounjour");
		
	    game.enterState(WindowGame.ID);
	  }

	

}
