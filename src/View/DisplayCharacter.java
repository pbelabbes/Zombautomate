package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class DisplayCharacter implements Observer {
	
	private float x,y;
	private int direction;
	private boolean moving;
	private Animation[] animations;
	private SpriteSheet walkSprite;
	
	public DisplayCharacter(float posX, float posY, int direction, int nbAnimations, String spritelink ) throws SlickException {
		setX(posX);
		setY(posY);
		this.direction = direction;
		animations = new Animation[nbAnimations];
		System.out.println(spritelink);
		walkSprite = new SpriteSheet(spritelink,64,64);
		moving = false;
		initAnimations();
	}

	private void initAnimations(){
		 this.animations[0] = loadAnimation(walkSprite, 0, 1, 0);
		    this.animations[1] = loadAnimation(walkSprite, 0, 1, 1);
		    this.animations[2] = loadAnimation(walkSprite, 0, 1, 2);
		    this.animations[3] = loadAnimation(walkSprite, 0, 1, 3);
		    this.animations[4] = loadAnimation(walkSprite, 1, 9, 0);
		    this.animations[5] = loadAnimation(walkSprite, 1, 9, 1);
		    this.animations[6] = loadAnimation(walkSprite, 1, 9, 2);
		    this.animations[7] = loadAnimation(walkSprite, 1, 9, 3);
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
	
	public Animation getCurrentAnimation(){
		return animations[direction + (moving ? 4 : 0)];
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

}
