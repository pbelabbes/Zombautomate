package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Model.Cell;
import Model.Decor;

public class DisplayCellule extends Display implements Observer {

	public static final int SIZE = 64;
	Cell cellule;

	public DisplayCellule(float posX, float posY, Cell c) throws SlickException{
		super(posX, posY, setSpriteSheet(c.getDecor()), 1);
		this.cellule = c;

	}

	private static SpriteSheet setSpriteSheet(Decor d) throws SlickException{
		String spritePath ="ressources/map/decors/sprite/";
		switch(d){

		case ROCK : spritePath += "rock.png";break;
		case GRASS : spritePath += "grass.png";break;
		case RABBIT : spritePath += "rabbit.png";break;
		case APPLE : spritePath += "apple.png";break;
		case BASEBALL_BAT : spritePath += "baseball_bat.png";break;
		case KATANA : spritePath += "katana.png";break;
		case TREE : spritePath += "tree.png";break;
		case SPROUT : spritePath += "sprout.png";break;
		}

		return new SpriteSheet(spritePath, 64, 64);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initAnimations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Animation getCurrentAnimation() {
		// TODO Auto-generated method stub
		return null;
	}

}
