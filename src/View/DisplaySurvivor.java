package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class DisplaySurvivor extends DisplayCharacter implements Observer {

	private Color color;
	

	public DisplaySurvivor(Model.Character c) throws SlickException{
		super(c, 8, "ressources/characters/sprites/male_walkcycle.png" );
//		this.color = 
		}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	

}
