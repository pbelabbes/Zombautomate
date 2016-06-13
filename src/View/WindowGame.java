package View;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import Model.Cell;
import Model.Map;

public class WindowGame extends BasicGame {
    private GameContainer container;
    private ArrayList<DisplayCharacter> characters = new ArrayList<DisplayCharacter>();
    private DisplayCellule[][] mapDisplay;
    
	public WindowGame(){
        super("Zombautomate by PANDAS");
    }
	
    
    public void init(GameContainer container) throws SlickException{
        this.container = container;
        //this.map = new TiledMap("ressources/map/map2.tmx");
        //Creation joueur bidon
    	this.characters.add(new DisplaySurvivor());
        
        //Creation map bidon
        int width = 500 , height = 500;
        Map map = new Map(width, height);
        Cell[][] grid = new Cell[width][height];
        for (int i = 0 ; i < grid.length;i++) {
			for ( int j = 0; j< grid[i].length;j++){
				grid[i][j] = new Cell(new Point(i,j));
			}
		}
        map.setGrid(grid);
                
        //Creation mapDisplay
        mapDisplay = new DisplayCellule[width][height];
        for(int i = 0 ; i< grid.length;i++){
        	for (int j = 0 ; j<grid.length;j++){
        		this.mapDisplay[i][j] = new DisplayCellule(DisplayCellule.SIZE * i,DisplayCellule.SIZE * j, map.getGrid()[i][j]);
        	}
        }
        
	}
    
    
    @Override
    public void keyPressed(int key, char c) {
    	DisplayCharacter dc = characters.get(0);
    	switch (key) {
            case Input.KEY_UP:    dc.setDirection(0); dc.setMoving(true); break;
            case Input.KEY_LEFT:  dc.setDirection(1); dc.setMoving(true); break;
            case Input.KEY_DOWN:  dc.setDirection(2); dc.setMoving(true); break;
            case Input.KEY_RIGHT: dc.setDirection(3); dc.setMoving(true); break;
        }
    }
    
    @Override
    public void keyReleased(int key, char c) {
    	DisplayCharacter dc = characters.get(0);
        dc.setMoving(false);
    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	//this.map.render(0, 0);
    	for (DisplayCellule[] c : mapDisplay) {
			for (DisplayCellule displayCellule : c) {
				g.drawAnimation(displayCellule.getCurrentAnimation(), displayCellule.getX(), displayCellule.getY());
			}
		}
    	for (DisplayCharacter c : characters) {
    		g.setColor(new Color(255,255,255, .5f));
    		g.fillOval(c.getX()-16, c.getY()-8, 32, 16);
    		g.drawAnimation(c.getCurrentAnimation(), c.getX()-32, c.getY()-60);
    	}
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	DisplayCharacter dc = characters.get(0); 
    	System.out.println(delta);
    	if (dc.isMoving()) {
    	        switch (dc.getDirection()) {
    	            case 0: dc.setY(dc.getY() - .1f * delta); break;
    	            case 1: dc.setX(dc.getX() - .1f * delta); break;
    	            case 2: dc.setY(dc.getY() + .1f * delta); break;
    	            case 3: dc.setX(dc.getX() + .1f * delta); break;
    	        }
    	    }
    }
    
    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 640, 480, false).start();
    }
}