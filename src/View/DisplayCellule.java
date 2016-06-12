package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Model.Cell;
import Model.Decor;

public class DisplayCellule extends Display implements Observer {

	public static final int SIZE = 32;
	Cell cellule;

	public DisplayCellule(float posX, float posY, Cell c) throws SlickException{
		super(posX, posY, setSpriteSheet(c.getDecor()), 8);
		this.cellule = c;

	}

	private static SpriteSheet setSpriteSheet(Decor d) throws SlickException{
		String spritePath = "ressources/map/tuiles/terrain_atlas_2.png";
		System.out.println(spritePath);
		return new SpriteSheet(spritePath, SIZE, SIZE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initAnimations() {
		this.animations[0] = loadAnimation(sprite, 0, 1, 25); // Grass
		this.animations[1] = loadAnimation(sprite, 26, 27, 25); //Rock
		this.animations[2] = loadAnimation(sprite, 12, 13, 24); //Apple ( Tomato )
		this.animations[3] = loadAnimation(sprite, 14, 15, 27); //Sprout
		this.animations[4] = loadAnimation(sprite, 13, 14, 31); //Tree
		this.animations[5] = loadAnimation(sprite, 10, 11, 5); //Katana
		this.animations[6] = loadAnimation(sprite, 23, 24, 20); //Baseball_bat
		this.animations[7] = loadAnimation(sprite, 15, 16, 15);//Rabbit

	}

	@Override
	public Animation getCurrentAnimation() {
		Animation selected;
		switch(this.cellule.getDecor())
		{
		case ROCK : selected = this.animations[1];break;
		case APPLE : selected = this.animations[2];break;
		case SPROUT : selected = this.animations[3];break;
		case TREE : selected = this.animations[4];break;
		case KATANA : selected = this.animations[5];break;
		case BASEBALL_BAT : selected = this.animations[6];break;
		case RABBIT : selected = this.animations[7];break;
		default : selected = this.animations[0]; 
		}
		return selected;
	}

}
