package View;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
//import org.newdawn.slick.SpriteSheet;
//import org.newdawn.slick.tiled.TiledMap;

import Model.Character;
import Model.*;

public class WindowGame extends BasicGame {
	//

	public static final int ID = 0;

	private GameContainer container;
	private ArrayList<DisplayCharacter> characters = new ArrayList<DisplayCharacter>();
	private ArrayList<Model.Character> charactersList;
	private DisplayCellule[][] mapDisplay;
	public  int width,height;
	public static int TILED_SIZE = 32;
	public int screenWidth , screenHeight;
	public Point mapOrigin = new Point(0,0);
	public Map map;
	private boolean isMoving =false;
	private int direction;

	public WindowGame() throws SlickException{
		super("Zombautomate by PANDAS");
	}

	public void initialisedGameModel(ArrayList<Model.Character> charactersList,Map map){
		this.map = map;
		this.charactersList = charactersList;
	}
	public void setScreenDimension(int width, int height){
		if(width > 0 && height > 0){
			this.screenWidth = width;
			this.screenHeight = height;
		}
	}

	public void init(GameContainer container) throws SlickException{
		this.container = container;
		for (Model.Character character : charactersList) {
			if(character instanceof Survivor){
				characters.add(new DisplaySurvivor(character));
			}else{
								characters.add(new DisplayZombie(character)); 
			}
		}

		//Creation mapDisplay
		mapDisplay = new DisplayCellule[map.getWidth()][map.getHeight()];
		for(int i = 0 ; i< map.getWidth();i++){
			for (int j = 0 ; j<map.getHeight();j++){
				this.mapDisplay[i][j] = new DisplayCellule(DisplayCellule.SIZE * i,DisplayCellule.SIZE * j, map.getGrid()[i][j]);
				//				System.out.println("Cellule mapdisplay["+i+"]["+j+"] :"+this.mapDisplay[i][j]);
			}
		}        
		//		System.out.println("taille map : "+map.getWidth()+" : "+map.getHeight());
		//		System.exit(0);
	}


	@Override
	public void keyPressed(int key, char c) {
		DisplayCharacter dc = characters.get(0);
		switch (key) {
		//Mouvement personnage
		case Input.KEY_UP:    dc.setDirection(0); dc.setMoving(true); break;
		case Input.KEY_LEFT:  dc.setDirection(1); dc.setMoving(true); break;
		case Input.KEY_DOWN:  dc.setDirection(2); dc.setMoving(true); break;
		case Input.KEY_RIGHT: dc.setDirection(3); dc.setMoving(true); break;

		//Mouvement Camera
		case Input.KEY_Z: this.direction = 0;this.isMoving=true;break;
		case Input.KEY_S: this.direction = 1;this.isMoving=true;break;
		case Input.KEY_Q: this.direction = 2;this.isMoving=true;break;
		case Input.KEY_D: this.direction = 3;this.isMoving=true;break;

		}
	}

	public void keyReleased(int key, char c) {
		DisplayCharacter dc = characters.get(0);
		dc.setMoving(false);
		this.isMoving = false;
	}


	public int getID() {
		return ID;
	}

	public void afficherDecors(GameContainer container, Graphics g, int mapOriginX, int mapOriginY){
		for(int cursorX = 0; cursorX >= 0 && cursorX < (screenWidth/TILED_SIZE) && cursorX < map.getWidth();cursorX++){
			for(int cursorY = 0; cursorY >= 0 && cursorY < (screenHeight/TILED_SIZE) && cursorY < map.getHeight();cursorY++){
				if(mapDisplay[mapOriginX+cursorX][mapOriginY+cursorY].getCell().getDecor()!=null){
					g.drawAnimation(mapDisplay[mapOriginX+cursorX][mapOriginY+cursorY].getCurrentAnimation(),cursorX*TILED_SIZE,cursorY*TILED_SIZE);
				}
				if(mapDisplay[mapOriginX+cursorX][mapOriginY+cursorY].getCell().getOwned_by() != null){
					g.setColor(Color.red);
					g.drawRect(cursorX, cursorY, TILED_SIZE, TILED_SIZE);
				}
			}
		}
	}

	public void afficherPersos(GameContainer container, Graphics g, int mapOriginX, int mapOriginY){
		for (DisplayCharacter c : characters) {

			if( c.getX() >= mapOriginX && c.getX() < mapOriginX+(screenWidth/TILED_SIZE) && c.getX() < map.getWidth() &&
					c.getY() >= mapOriginY && c.getY() < mapOriginY+(screenHeight/TILED_SIZE) && c.getY() < map.getHeight())
			{
				int posCharScreenX = (int) (c.getX()- mapOriginX);
				int posCharScreenY = (int) (c.getY()- mapOriginY);
				g.setColor(new Color(255,255,255, .5f));
				g.fillOval(posCharScreenX*TILED_SIZE-16, posCharScreenY*TILED_SIZE-8, 32, 16);
				g.drawAnimation(c.getCurrentAnimation(), posCharScreenX*TILED_SIZE-32, posCharScreenY*TILED_SIZE-60);
			}
		}
	}

	public void afficherAutomates(GameContainer container, Graphics g, int mapOriginX, int mapOriginY){
		System.out.println("afficherAutomates");
		for (DisplayCharacter c : characters) {
			System.out.println(c.getCharacter().getAutomata().getPosition());
			Automata automate= c.getCharacter().getAutomata();
			Point posAutom = automate.getPosition();
			int heightAutom = automate.getHeight();
			int widthAutom = automate.getWidth();


			if( posAutom.x >= mapOriginX && posAutom.x < mapOriginX+(screenWidth/TILED_SIZE) && posAutom.x < map.getWidth() &&
					posAutom.y >= mapOriginY && posAutom.y < mapOriginY+(screenHeight/TILED_SIZE) && posAutom.y < map.getHeight())
			{
				g.setColor(Color.black);
				g.drawRect(posAutom.x, posAutom.y, widthAutom, heightAutom);
			}
		}
	}

	public void afficherInfos(GameContainer container, Graphics g){
		g.setColor(Color.white);
		g.drawString("mapOrigin : "+mapOrigin.x+";"+mapOrigin.y, 0, 30);
		g.drawString("Taille Map en pixels: "+map.getWidth()*TILED_SIZE+" : "+map.getHeight()*TILED_SIZE, 0, 50);
		g.drawString("Taille de l'�cran en pixels : "+screenWidth+" : "+screenHeight, 0, 70);
		g.drawString("mapOriginMax : "+(map.getWidth()-screenWidth/TILED_SIZE)+" : "+(map.getHeight()-screenHeight/TILED_SIZE), 0, 90);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		int mapOriginX = this.mapOrigin.x, mapOriginY = this.mapOrigin.y;

		//Affichage de d�cors
		afficherDecors(container, g, mapOriginX,mapOriginY);

		//Affichage des personnages
		afficherPersos(container, g, mapOriginX,mapOriginY);


		//Affichage Automates
//		afficherAutomates(container, g, mapOriginX, mapOriginY);

		//Affichage infos
//		afficherInfos(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		DisplayCharacter dc = characters.get(0); 
		//    	System.out.println(delta);
		//    	if (dc.isMoving()) {
		//    	        switch (dc.getDirection()) {
		//    	            case 0: dc.setY(dc.getY() - .1f * delta); break;
		//    	            case 1: dc.setX(dc.getX() - .1f * delta); break;
		//    	            case 2: dc.setY(dc.getY() + .1f * delta); break;
		//    	            case 3: dc.setX(dc.getX() + .1f * delta); break;
		//    	        }
		//    	    }

		if(this.isMoving){
			switch(this.direction){
			case 0: if(this.mapOrigin.y > 0) this.mapOrigin.y--;break;
			case 1: if(this.mapOrigin.y < (map.getHeight()-screenHeight/TILED_SIZE)) this.mapOrigin.y++;break;
			case 2: if(this.mapOrigin.x > 0)this.mapOrigin.x--;break;
			case 3: if(this.mapOrigin.x< (map.getWidth()-screenWidth/TILED_SIZE)) this.mapOrigin.x++;break;

			}
		}
	}

	public static void main(String[] args) throws SlickException {
		ArrayList<Character> lC = StateGame.jeu(2) ; 
		Map carte = Moteur.initiate_map(lC, StateGame.getZombies());
		WindowGame wg = new WindowGame();
		wg.initialisedGameModel(lC, carte);
		AppGameContainer tmp = new AppGameContainer(null);
		AppGameContainer app= new AppGameContainer(wg,tmp.getScreenWidth(),tmp.getScreenHeight(),false);
		wg.setScreenDimension(tmp.getScreenWidth(),tmp.getScreenHeight());
		System.out.println(wg.screenWidth+"/"+tmp.getScreenWidth()+" "+wg.screenHeight+"/"+app.getScreenHeight());
		app.start();
	}
}