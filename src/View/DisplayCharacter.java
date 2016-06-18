package View;

import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import Model.Character;

public abstract class DisplayCharacter extends Display implements Observer {
	
	protected int direction;
	protected boolean moving;
	protected int action;
	protected Character character;
	
	public DisplayCharacter(Character c, int nbAnimations, String spriteName) throws SlickException {
		super(c.getCell().getPosition().x, c.getCell().getPosition().y, new SpriteSheet(spriteName, 64, 64), nbAnimations);
		this.setDirection(0);
		this.setMoving(false);
		this.setAction(0);
		this.character = c;
		}

	public void initAnimations(){
			//d�placement 
			this.animations[0] = loadAnimation(sprite, 0, 1, 10);//sud immobile
		    this.animations[1] = loadAnimation(sprite, 0, 1, 9);//ouest immobile
		    this.animations[2] = loadAnimation(sprite, 0, 1, 11);//est immobile
		    this.animations[3] = loadAnimation(sprite, 0, 1, 8);//nord immobile
		    //animation deplacement
		    this.animations[4] = loadAnimation(sprite, 1, 9, 10);//sud mobile
		    this.animations[5] = loadAnimation(sprite, 1, 9, 9);//ouest mobile
		    this.animations[6] = loadAnimation(sprite, 1, 9, 11);//est mobile
		    this.animations[7] = loadAnimation(sprite, 1, 9, 8);//nord mobile
		    
		    //attaque couteau
		    this.animations[8] = loadAnimation(sprite, 0, 1, 14);//sud knife attaque
		    this.animations[9] = loadAnimation(sprite, 0, 1, 13);//ouest knife attaque
		    this.animations[10] = loadAnimation(sprite, 0, 1, 15);//est knife attaque
		    this.animations[11] = loadAnimation(sprite, 0, 1, 12);//nord knife attaque
		    //animation
		    this.animations[12] = loadAnimation(sprite, 1, 6, 14);//sud knife attaque
		    this.animations[13] = loadAnimation(sprite, 1, 6, 13);//ouest knife attaque
		    this.animations[14] = loadAnimation(sprite, 1, 6, 15);//est knife attaque
		    this.animations[15] = loadAnimation(sprite, 1, 6, 12);//nord knife attaque
		    
		    //attaque lance
		    this.animations[16] = loadAnimation(sprite, 0, 1, 6);//sud spire attaque
		    this.animations[17] = loadAnimation(sprite, 0, 1, 5);//ouest spire attaque
		    this.animations[18] = loadAnimation(sprite, 0, 1, 7);//est spire attaque
		    this.animations[19] = loadAnimation(sprite, 0, 1, 4);//nord spire attaque
		    //animations
		    this.animations[20] = loadAnimation(sprite, 1, 8, 6);//sud spire attaque
		    this.animations[21] = loadAnimation(sprite, 1, 8, 5);//ouest spire attaque
		    this.animations[22] = loadAnimation(sprite, 1, 8, 7);//est spire attaque
		    this.animations[23] = loadAnimation(sprite, 1, 8, 4);//nord spire attaque
		    
		    //attaque sans arme
		    this.animations[24] = loadAnimation(sprite, 1, 13, 18);//sud mobile
		    this.animations[25] = loadAnimation(sprite, 1, 13, 17);//ouest mobile
		    this.animations[26] = loadAnimation(sprite, 1, 13, 19);//est mobile
		    this.animations[27] = loadAnimation(sprite, 1, 13, 16);//nord mobile
		    
		    //autres actions
		    this.animations[28] = loadAnimation(sprite, 1, 7, 2);//sud mobile
		    this.animations[29] = loadAnimation(sprite, 1, 7, 1);//ouest mobile
		    this.animations[30] = loadAnimation(sprite, 1, 7, 3);//est mobile
		    this.animations[31] = loadAnimation(sprite, 1, 7, 0);//nord mobile
	}
	
	public Animation getCurrentAnimation(){
		/*System.out.println("anim: "+(this.direction));
		Animation ani = animations[this.direction + action*4];
		System.out.println("anim: "+ani);*/
		return animations[this.direction + action*4];
	}
	
	@Override
	public void update(Observable o, Object arg) {
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void setAction(int action){
		this.action = action;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public Model.Character getCharacter(){
		return this.character;
	}

}
