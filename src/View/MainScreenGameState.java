package View;




import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background;
	private Image Continue;
	private Image Newgame;
	private Image Option;
	private StateBasedGame game;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		this.Newgame = new Image ("../Zombautomate/ressources/Menu/Newgame.png");
		this.Continue = new Image ("../Zombautomate/ressources/Menu/Continue.png");
		this.Option = new Image ("../Zombautomate/ressources/Menu/Option.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		background.draw(0, 0, container.getWidth(), container.getHeight());
		//g.drawString("Appuyer sur une touche", 300, 300);
		Newgame.draw(larg/2 - larg/10 ,haut/2 - haut/30 -haut/18 , larg/5, haut/7);
		Continue.draw(larg/2 - larg/10 ,haut/2 + haut/7 - haut/18 , larg/5, haut/7);
		Option.draw(larg/2 - larg/10 ,haut/2 + haut/7 + haut/7 + haut/30 - haut/18 , larg/5, haut/7);
		
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
	    game.enterState(MenuTypeJeu.ID);
	  }

	

}
