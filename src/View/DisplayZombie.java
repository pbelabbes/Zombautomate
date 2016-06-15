package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.SlickException;

import Model.Character;

public class DisplayZombie extends DisplayCharacter implements Observer{
	
	public DisplayZombie(Model.Character c) throws SlickException{
		super(c, 8, "ressources/characters/sprites/zombie_walkcycle.png" );
		}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

}
