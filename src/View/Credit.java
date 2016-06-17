package View;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Credit extends BasicGameState {
	public static final int ID = 5;
	private Image background ; 
	private StateBasedGame game ; 
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game ; 
		this .background = new Image("../Zombautomate/ressources/Menu/backround_credit.png");

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		int larg = container.getWidth()  ;
		int haut = container.getHeight() ;
		g.setColor(Color.black);
//		g.setFont(font);
		
		background.draw(0, 0, container.getWidth(), container.getHeight()-55);
		
		g.drawString("Joueur1", larg/4 , haut/4  );
		

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {


	}
	
	 public void keyReleased(int key, char c) {
		    //game.enterState(MapGameState.ID);
		 System.out.println("touche pressé ");
		  }

	@Override
	public int getID() {
		return ID;
	}

}
