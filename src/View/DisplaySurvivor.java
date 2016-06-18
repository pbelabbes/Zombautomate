package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class DisplaySurvivor extends DisplayCharacter implements Observer {

	private Color color;
	

	public DisplaySurvivor(model.jeu.Character c) throws SlickException{
		super(c, 8, "ressources/characters/sprites/male_walkcycle.png" );
		this.color = randomColor();
		}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public static Color randomColor(){
		int red = (int) (Math.random()*255),blue = (int) (Math.random()*255),green = (int) (Math.random()*255);
		System.out.println("red : "+red+", blue : "+blue+", green : "+green);
		return new Color(1,red,blue,green);
	}
}
