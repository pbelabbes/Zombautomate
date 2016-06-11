package View;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private ArrayList<DisplayCharacter> characters;
	private TiledMap map;
    
	public WindowGame() {
        super("Lesson 1 :: WindowGame");
    }
	
    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("../ressources/map/map.tmx");
        this.characters.add(new DisplaySurvivor());
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.map.render(0, 0);
    	for (DisplayCharacter c : characters) {
    		g.drawAnimation(c.getCurrentAnimation(), c.getX(), c.getY());
    	}
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }
    
    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 640, 480, false).start();
    }
}