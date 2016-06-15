package View;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		 addState(new MainScreenGameState());
		 addState(new WindowsGame());
	}
	
	public StateGame() {
	    super("ZOMBAUTOMATE V1");
	  }
	
	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new StateGame(), 800, 600, false).start();

	}

}
