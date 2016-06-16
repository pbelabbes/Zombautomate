package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Model.Cell;
import Model.Character;

public class DisplayZombie extends DisplayCharacter implements Observer{

	public static final int SIZE = 32;
	public static SpriteSheet SPRITE = null;
	public static Animation[] ANIMATIONS = null;
	public static boolean UPDATE = false;
	
	private Cell cellule;

	public DisplayZombie(Model.Character c) throws SlickException{
		super(c, 8, "ressources/characters/sprites/zombie_walkcycle.png" );
	}

	@Override
	public void update(Observable arg0, Object arg1) {


	}


	@Override
	public void initAnimations() {

		if( ANIMATIONS == null ){
			this.animations[0] = loadAnimation(this.sprite, 0, 1, 0); //Zombie face immobile
			this.animations[1] = loadAnimation(this.sprite, 0, 1, 1); //Zombie ouest immobile
			this.animations[2] = loadAnimation(this.sprite, 3, 4, 1); //Zombie est immobile
			this.animations[3] = loadAnimation(this.sprite, 0, 1, 2); //Zombie nord immobile
			this.animations[4] = loadAnimation(this.sprite, 0, 6, 0); //Zombie face marche
			this.animations[5] = loadAnimation(this.sprite, 0, 3, 1); //Zombie ouest marche
			this.animations[6] = loadAnimation(this.sprite, 3, 6, 1); //Zombie est marche
			this.animations[7] = loadAnimation(this.sprite, 0, 4, 2); //Zombie nord marche
			ANIMATIONS = this.animations;
		}
		else this.animations = ANIMATIONS;

	}



}
