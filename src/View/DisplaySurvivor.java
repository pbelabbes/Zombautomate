package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class DisplaySurvivor extends DisplayCharacter implements Observer {

	

	public DisplaySurvivor() throws SlickException{
		super(300, 300, 0, 8, "../ressources/characters/sprites/male_walkcycle.png");
	}
	
	public DisplaySurvivor(float posX, float posY, int direction) throws SlickException{
		super(posX, posY, direction, 8, "../ressources/characters/sprites/male_walkcycle.png");
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	

}
