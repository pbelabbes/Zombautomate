package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import Model.Character;

public abstract class DisplayCharacter extends Display implements Observer {
	
	private int direction;
	private boolean moving;
	private Character character;
	
//	public DisplayCharacter(float posX, float posY, int direction, int nbAnimations, String spriteName) throws SlickException {
	public DisplayCharacter(Character c, int nbAnimations, String spriteName) throws SlickException {
		super(c.getCell().getPosition().x, c.getCell().getPosition().y, new SpriteSheet(spriteName, 64, 64), nbAnimations);
//		super(posX, posY, new SpriteSheet(spriteName, 64, 64), nbAnimations);
		this.setDirection(direction);
		setMoving(false);
		}

	public void initAnimations(){
		 this.animations[0] = loadAnimation(sprite, 0, 1, 0);
		    this.animations[1] = loadAnimation(sprite, 0, 1, 1);
		    this.animations[2] = loadAnimation(sprite, 0, 1, 2);
		    this.animations[3] = loadAnimation(sprite, 0, 1, 3);
		    this.animations[4] = loadAnimation(sprite, 1, 9, 0);
		    this.animations[5] = loadAnimation(sprite, 1, 9, 1);
		    this.animations[6] = loadAnimation(sprite, 1, 9, 2);
		    this.animations[7] = loadAnimation(sprite, 1, 9, 3);
	}
	
	public Animation getCurrentAnimation(){
		return animations[this.direction + (moving ? 4 : 0)];
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
