package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Display implements Observer {

	protected float x,y;
	public static int tempsAnim = 200;
	protected Animation[] animations;
	protected SpriteSheet sprite;

	public Display(float posX, float posY, SpriteSheet sprite, int nbAnimation) throws SlickException{
		setX(posX);
		setY(posY);
		setTempsAnim(tempsAnim);

		animations = new Animation[nbAnimation];
		this.sprite = sprite;
		initAnimations();
	}

	public abstract void initAnimations();

	public static void changeSpeed() {
		if(tempsAnim == 200) tempsAnim= 50; else if ( tempsAnim== 50) tempsAnim= 20; else tempsAnim= 200;

	}

	protected static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	public abstract Animation getCurrentAnimation();

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

	public int getTempsAnim(){
		return this.tempsAnim;
	}
	public void setTempsAnim(int tempsAnim){
		this.tempsAnim=tempsAnim;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}

}
