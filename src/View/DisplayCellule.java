package View;
import model.decors.*;
import model.jeu.*;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/*import Model.Cell;
import Model.Decor;
*/
public class DisplayCellule extends Display implements Observer {

	public static final int SIZE = 32;
	public static SpriteSheet SPRITE = null;
	public static Animation[] ANIMATIONS = null;
	public static boolean UPDATE = false;

	private Cell cellule;

	public DisplayCellule(float posX, float posY, Cell c) throws SlickException{
		super(posX, posY, setSpriteSheet(), 8);
		this.cellule = c;

	}

	private static SpriteSheet setSpriteSheet() throws SlickException{
		if(SPRITE == null){
			String spritePath = "ressources/map/tuiles/sprites.png";
			SPRITE = new SpriteSheet(spritePath, SIZE, SIZE);
		}

		return SPRITE;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initAnimations() {
	
		if( ANIMATIONS == null ){
			this.animations[0] = loadAnimation(sprite, 0, 1, 0); // Grass
			this.animations[1] = loadAnimation(sprite, 0, 1, 1); //Rock
			this.animations[2] = loadAnimation(sprite, 0, 1, 2); //Apple ( Tomato )
			this.animations[3] = loadAnimation(sprite, 0, 1, 3); //Sprout
			this.animations[4] = loadAnimation(sprite, 0, 1, 7); //Tree
			this.animations[5] = loadAnimation(sprite, 0, 1, 6); //Katana
			this.animations[6] = loadAnimation(sprite, 0, 1, 5); //Baseball_bat
			this.animations[7] = loadAnimation(sprite, 0, 1, 4);//Rabbit
			ANIMATIONS = this.animations;
		}
		else this.animations = ANIMATIONS;
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

	public model.jeu.Character GetCharacOn(){
		return cellule.getEntity_on();
	}
	
	public Cell getCell(){
		return cellule;
	}
}
