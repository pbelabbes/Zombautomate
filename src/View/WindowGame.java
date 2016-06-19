package View;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
//import org.newdawn.slick.SpriteSheet;
//import org.newdawn.slick.tiled.TiledMap;

import Model.Character;
import Model.*;

public class WindowGame extends BasicGameState {
	//
	public final static float MAXLEVEL = .8f;
	
	public static final int ID = 0;
	public static ArrayList<Model.Character> charactersList;
	public static int TILED_SIZE = 32;
	public static int screenWidth , screenHeight;
	public  static Map map;
	public static Ordonnanceur ordo;

	
	public Point mapOrigin = new Point(0,0);

	private Music music;
	private float musicLevel;
	private GameContainer container;
	private ArrayList<DisplayCharacter> characters = new ArrayList<DisplayCharacter>();
	private DisplayCellule[][] mapDisplay;
	public  int width,height;
	private boolean isMoving =false;
	private int dureeAnim=0;
	private DisplayCharacter currentChar;
	private int direction;
	private StateBasedGame game;
	private float vitesse;
	private Player zombies;
	private boolean showInfo;
	private boolean showplayers;
	private boolean showChara;
	private boolean gameOver;


	public void init(GameContainer container,StateBasedGame game) throws SlickException{
		this.container = container;
		this.vitesse = 0.0005f;
		this.showInfo = false;
		this.game = game;
		this.showplayers = false;
		this.showChara = false ;
		this.music = new Music("../Zombautomate/ressources/song/ingame2.ogg");
		music.fade(100, .1f, false);
		System.out.println("\n\nje suis dans le init"+container.getScreenWidth()+ container.getScreenHeight()+"\n\n");
	}

	//		setScreenDimension(container.getScreenWidth(),container.getScreenHeight());


	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.music.fade(100, 0f, true);
		if((System.getProperties().get("os.name")).equals("Linux") ){

			MainScreenGameState.music.stop();
			EndGameView.music.loop();
		}
		super.leave(container, game);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		System.out.println("\n\nje suis dans le enter\n\n");
		this.gameOver = false ;

		if((System.getProperties().get("os.name")).equals("Linux")){
			MainScreenGameState.music.stop() ;
			EndGameView.music.stop();
		}
		this.musicLevel = MAXLEVEL;
		music.fade(100, musicLevel, false);
		music.loop();

		super.enter(container, game);
		for (Model.Character character : charactersList) {
			if(character instanceof Survivor){
				characters.add(new DisplaySurvivor(character));
			}else{
				characters.add(new DisplayZombie(character)); 
				this.zombies = character.getPlayer(); 
			}
		}

		System.out.println("\n\n je suis entre \n\n");

		//Creation mapDisplay
		mapDisplay = new DisplayCellule[map.getWidth()][map.getHeight()];
		for(int i = 0 ; i< map.getWidth();i++){
			for (int j = 0 ; j<map.getHeight();j++){
				this.mapDisplay[i][j] = new DisplayCellule(DisplayCellule.SIZE * i,DisplayCellule.SIZE * j, map.getGrid()[i][j]);
			}
		}     

		System.out.println("\n\n je suis dans\n\n ");

	}



	public void setScreenDimension(int width, int height){
		if(width > 0 && height > 0){
			screenWidth = width;
			screenHeight = height-TILED_SIZE;
		}
	}


	public void cleanCharacters(){
		DisplayCharacter toDelete = null;
		for (DisplayCharacter displayCharacter : characters) {
			boolean estPresent = false;
			for (Character c : charactersList) {
				estPresent |= (c == displayCharacter.getCharacter());
			}
			if(!estPresent) toDelete = displayCharacter; 
		}
		if(toDelete != null ){
			characters.remove(toDelete);
		}
	}

	@Override
	public void keyPressed(int key, char c) {

		if (!gameOver){
			DisplayCharacter dc = characters.get(0);


			switch (key) {
			//Mouvement personnage
			case Input.KEY_UP:    dc.setDirection(0); dc.setMoving(true); break;
			case Input.KEY_LEFT:  dc.setDirection(1); dc.setMoving(true); break;
			case Input.KEY_DOWN:  dc.setDirection(2); dc.setMoving(true); break;
			case Input.KEY_RIGHT: dc.setDirection(3); dc.setMoving(true); break;

			//Changement vitesse
			case Input.KEY_SPACE : Display.changeSpeed();break;
			}
		}

		switch (key) {
		//Mouvement Camera
		case Input.KEY_Z: this.direction = 0;this.isMoving=true;break;
		case Input.KEY_S: this.direction = 1;this.isMoving=true;break;
		case Input.KEY_Q: this.direction = 2;this.isMoving=true;break;
		case Input.KEY_D: this.direction = 3;this.isMoving=true;break;


		//affichage info
		case Input.KEY_I : this.changeInfo();break;

		//couper le son
		case Input.KEY_M : this.changeSound();break;
		
		//afficher les joueurs
		case Input.KEY_LSHIFT: this.changeShplayer(); break; 
		
		//afficher les charactere
		case Input.KEY_LCONTROL: this.changeShChara(); break;
		}

	}

	private void changeShChara() {
		this.showChara= !this.showChara ; 
		
	}

	private void changeSound() {
		this.musicLevel = (this.musicLevel > 0f)?0f:MAXLEVEL;
		this.music.fade(100, this.musicLevel, false);

	}

	private void changeInfo() {
		this.showInfo = !this.showInfo;
	}

	private void changeShplayer(){
		this.showplayers = !this.showplayers;
	}

	public void keyReleased(int key, char c) {

		if(!gameOver){
			DisplayCharacter dc = characters.get(0);
			dc.setMoving(false);
			this.isMoving = false;
		}
		else{
			if (key == Input.KEY_ENTER){
				game.enterState(EndGameView.ID);
			}
		}

	}


	public int getID() {
		return ID;
	}

	public void afficherDecors(GameContainer container, Graphics g, int mapOriginX, int mapOriginY){

		for(int cursorX = 0; cursorX >= 0 && cursorX < (screenWidth/TILED_SIZE) && cursorX < map.getWidth();cursorX++){

			for(int cursorY = 0; cursorY >= 0 && cursorY < (screenHeight/TILED_SIZE) && cursorY < map.getHeight();cursorY++){
//mettre cursorY à 0 pour éviter un bord noir + plantage en lançant une 2eme partie
				DisplayCellule cCell = mapDisplay[mapOriginX+cursorX][mapOriginY+cursorY];
				if(cCell.getCell().getDecor()!=null){
					g.drawAnimation(cCell.getCurrentAnimation(),cursorX*TILED_SIZE,cursorY*TILED_SIZE);
				}
				if(currentChar!= null && cCell.getCell().getOwned_by() == currentChar.getCharacter()){
					g.setColor(((DisplaySurvivor) currentChar).getColor());
					g.fillRect(cursorX*TILED_SIZE, cursorY*TILED_SIZE, TILED_SIZE, TILED_SIZE);
				}

//				if(cCell.getCell().getEntity_on() != null){
//					if(cCell.getCell().getEntity_on() instanceof Zombie){
//						g.setColor(Color.green);						
//					}else{
//						for (DisplayCharacter displayCharacter : characters) {
//							if(displayCharacter.getCharacter() == cCell.getCell().getEntity_on()) g.setColor(((DisplaySurvivor) displayCharacter).getColor());
//						}
//
//					}
//					g.fillRect(cursorX*TILED_SIZE, cursorY*TILED_SIZE, TILED_SIZE, TILED_SIZE);
//
//				}
			}
		}
	}
	
	public void afficheChar(GameContainer container, Graphics g){
		if(!gameOver){
			if (this.currentChar != null){
			g.setColor(Color.darkGray);
			g.fillRect(screenWidth/3, screenHeight - 20-screenHeight/12, 370, 20);
			g.setColor(Color.pink);
			g.drawString("charactere: hp : " + this.currentChar.getCharacter().getHp() +" joueur " +this.currentChar.getCharacter().getPlayer().getName(), screenWidth/3 + 7, screenHeight - 17-screenHeight/12 );
			}
		}
		
	}
	
	public void affichePLayer(GameContainer container, Graphics g){
		if (!gameOver){
			g.setColor(Color.darkGray);
			g.fillRect(0, screenHeight - 100-screenHeight/12 - 5, 150, 120);
			g.setColor(Color.magenta);
			g.drawString("PLAYER 1   : " , 0, screenHeight - 80-20-screenHeight/12);
			g.drawString("FOODSTOCK  : "+ ordo.getPlayer(1).getFoodStock(), 0, screenHeight - 60-20-screenHeight/12);
			g.drawString("STONESOTCK : "+ ordo.getPlayer(1).getStone(), 0, screenHeight - 40-20-screenHeight/12);
			g.drawString("SEEDSTOCK  : "+ ordo.getPlayer(1).getSeed(), 0, screenHeight-  20-20 -screenHeight/12 );
			g.drawString("Chara. rem.: "+ ordo.getPlayer(1).characters_remaining(), 0, screenHeight-20 -screenHeight/12 );

			if(EcranDeValidation.mode == 2 || Math.abs(EcranDeValidation.mode) == 5){
				g.setColor(Color.darkGray);
				g.fillRect(4*screenWidth/5, screenHeight - 100-screenHeight/12 - 5, 150, 120);
				g.setColor(Color.magenta);
				g.drawString("PLAYER 2   : ", 4*screenWidth/5, screenHeight - 80 -20-screenHeight/12);
				g.drawString("FOODSTOCK  : "+ ordo.getPlayer(2).getFoodStock(), 4*screenWidth/5, screenHeight -20- 60-screenHeight/12);
				g.drawString("STONESTOCK : "+ ordo.getPlayer(2).getFoodStock(),4*screenWidth/5, screenHeight-20 - 40-screenHeight/12);
				g.drawString("SEEDSTOCK  : "+ ordo.getPlayer(2).getSeed(), 4*screenWidth/5, screenHeight-  20-20 -screenHeight/12 );
				g.drawString("Chara. rem.: "+ ordo.getPlayer(2).characters_remaining(),  4*screenWidth/5, screenHeight -20-screenHeight/12 );

			}
			g.setColor(Color.white);
		}
	}


	public void afficherPersos(GameContainer container, Graphics g, int mapOriginX, int mapOriginY){

		for (DisplayCharacter c : characters) {
			if( c.getX() >= mapOriginX && c.getX() < mapOriginX+(screenWidth/TILED_SIZE) && c.getX() < map.getWidth() &&
					c.getY() >= mapOriginY && c.getY() < mapOriginY+(screenHeight/TILED_SIZE) && c.getY() < map.getHeight())
			{
				float posCharScreenX = (c.getX()- mapOriginX) *TILED_SIZE-TILED_SIZE/2;
				float posCharScreenY = (c.getY()- mapOriginY) *TILED_SIZE-TILED_SIZE-16;

				if(c instanceof DisplaySurvivor){
					g.setColor(((DisplaySurvivor) c).getColor());
					g.fillOval(posCharScreenX+16, posCharScreenY+56, 32, 16);
				}
				//System.out.println(c.getCurrentAnimation());
				System.out.println("windowGame afficher perso : x"+c.getX()+" y"+c.getY()+"\n");
				g.drawAnimation(c.getCurrentAnimation(), posCharScreenX, posCharScreenY);


			}
		}
	}

	public void afficherInfos(GameContainer container, Graphics g){
		g.setColor(Color.white);
		g.drawString("mapOrigin : "+mapOrigin.x+";"+mapOrigin.y, 0, 30);
		g.drawString("Taille Map en pixels: "+map.getWidth()*TILED_SIZE+" : "+map.getHeight()*TILED_SIZE, 0, 50);
		g.drawString("Taille de l'ecran en pixels : "+screenWidth+" : "+screenHeight, 0, 70);
		g.drawString("mapOriginMax : "+(map.getWidth()-screenWidth/TILED_SIZE)+" : "+(map.getHeight()-screenHeight/TILED_SIZE), 0, 90);
		if (ordo.getAction()!=null)	g.drawString("Action en cours : "+ordo.getAction(), 0, 110);
		else g.drawString("Action en cours : rien", 0, 110);
		g.drawString("Vitesse : "+ Display.tempsAnim, 0, 130);
		g.drawString("Tour n : "+ordo.getTurn(), 0, 150);
		g.drawString("Nombre de Zombies : "+(ordo.get_remaining_zombies()), 0, 170);
	}

	public void afficherGameOver(GameContainer container, Graphics g){
		Image image;
		g.setColor(Color.black);
		g.fillRect(0, 0, screenWidth, height);
		try {
			image = new Image("ressources/end/game_over.png");
			g.drawImage(image, (screenWidth/2)-image.getWidth()/2, (screenHeight/2)-image.getHeight()/2);
			g.setColor(Color.red);
			g.drawString("appuyer sur enter pour acceder a l'ecran des scores", screenWidth/3, 2*screenHeight/3);

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {

		//System.out.println("Map Origin :" + this.mapOrigin.x+":"+this.mapOrigin.y);
		int mapOriginX = this.mapOrigin.x, mapOriginY = this.mapOrigin.y;

		//Affichage de décors
		afficherDecors(container, g, mapOriginX,mapOriginY);

		//Affichage des personnages
		afficherPersos(container, g, mapOriginX,mapOriginY);

		if(this.gameOver) afficherGameOver(container,g);

		//Affichage Automates
		//afficherAutomates(container, g, mapOriginX, mapOriginY);

		//Affichage infos
		if(this.showInfo)afficherInfos(container, g);


		if(this.showplayers)affichePLayer(container, g);
		
		if(this.showChara)afficheChar(container, g);

	}

	@Override
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {

		if(!this.gameOver){
			DisplayCharacter cCharac = null;
			for (DisplayCharacter c : characters) {
				if(c.getCharacter() == ordo.getCharacter()){
					cCharac = c;
				}
			}
			//this.currentChar = cCharac;
			if(cCharac != null){
				if (!cCharac.moving){
					//Si le personnage a fini son action on change

					int tour = ordo.getTurn(); //on enregistre le tour actuel pour voir si l'appel de next() fait changer de tour
					
					ordo.next();
					if(tour != ordo.getTurn())
					{
						this.addZombie();
					}
					
					cCharac = null;
					for (DisplayCharacter c : characters) {
						if(c.getCharacter() == ordo.getCharacter()){
							cCharac = c;
						}
					}
					this.currentChar = cCharac;
					if(cCharac != null){
						cCharac.setMoving(true);
						switch (ordo.getDirection()){
						case 'U': break;
						case 'S': cCharac.setDirection(0); break;
						case 'O': cCharac.setDirection(1); break;
						case 'E': cCharac.setDirection(2); break;
						case 'N': cCharac.setDirection(3); break;
						default:;
						}
					}
				}
				else{
					//On continue d'afficher l'action du perso precedent

					if (ordo.getAction()!=null){
						switch (ordo.getAction()){
						case MOVE://animation1
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								System.out.println("WindGame: ccharac.char.cell  x="+ cCharac.getCharacter().getCell().getPosition().x +" y=" +cCharac.getCharacter().getCell().getPosition().y+"\n");
								cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
								cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
								cCharac.setAction(0);
								this.dureeAnim=0;
								cCharac.setMoving(false);
							}
							else {
								this.dureeAnim++;
								cCharac.setAction(1);
								switch (cCharac.getDirection()) {
								case 0:
									cCharac.setY(cCharac.getY() + vitesse * delta); 
									if (cCharac.getY()>=cCharac.getCharacter().getCell().getPosition().y){
										cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
										//cCharac.setMoving(false);
										//cCharac.setAction(0);
									}
									break;
								case 1:
									cCharac.setX(cCharac.getX() - vitesse * delta); 
									if (cCharac.getX()<=cCharac.getCharacter().getCell().getPosition().x){
										cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
										//cCharac.setMoving(false);
										//cCharac.setAction(0);

									}
									break;
								case 2:
									cCharac.setX(cCharac.getX() + vitesse * delta); 
									if (cCharac.getX()>=cCharac.getCharacter().getCell().getPosition().x){
										cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
										//cCharac.setMoving(false);
										//cCharac.setAction(0);

									}
									break;
								case 3:
									cCharac.setY(cCharac.getY() - vitesse * delta); 
									if (cCharac.getY()<=cCharac.getCharacter().getCell().getPosition().y){
										cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
										//cCharac.setMoving(false);
										//cCharac.setAction(0);

									}
									break;
								default :
								}
							}
							break;

						case ATTACK://animations 2 et 3 couteau  4 et 5 lance 6 poing
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								if (cCharac.getCharacter() instanceof Survivor){
									if (((Survivor) cCharac.getCharacter()).getWeapon().size()>0){
										Arme arme=((Survivor) cCharac.getCharacter()).getWeapon().get(0);
										if (arme instanceof Baseball_Bat) cCharac.setAction(2);
										else cCharac.setAction(4);
									}
									else cCharac.setAction(0);
								}
								this.dureeAnim=0;
								cCharac.setMoving(false);

							}
							else {
								this.dureeAnim++;
								if (cCharac.getCharacter() instanceof Survivor){
									if (((Survivor) cCharac.getCharacter()).getWeapon().size()>0){
										Arme arme=((Survivor) cCharac.getCharacter()).getWeapon().get(0);
										if (arme instanceof Baseball_Bat) cCharac.setAction(3);
										else cCharac.setAction(5);
									}
									else cCharac.setAction(6);
								}
							}
							break;

						case DROP:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);

							}
							else {this.dureeAnim++;cCharac.setAction(7);}


							break;

						case PICK:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);

							}
							else {this.dureeAnim++;cCharac.setAction(7);}

							break;

						case PLANT:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);
							}
							else {this.dureeAnim++;cCharac.setAction(7);}

							break;

						case STEAL:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);
							}
							else {this.dureeAnim++;cCharac.setAction(7);}

							break;

						case SWAP:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);
							}
							else {this.dureeAnim++;cCharac.setAction(7);}

							break;

						case WATER:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);
							}
							else {this.dureeAnim++;cCharac.setAction(7);}

							break;

						default:
							cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
							cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
							if (cCharac.getTempsAnim()<=this.dureeAnim){
								this.dureeAnim=0;
								cCharac.setAction(0);
								cCharac.setMoving(false);
							}
							else {this.dureeAnim++;cCharac.setAction(7);}

							break;
						}
					}
					else {
						cCharac.setY(cCharac.getCharacter().getCell().getPosition().y);
						cCharac.setX(cCharac.getCharacter().getCell().getPosition().x);
						if (cCharac.getTempsAnim()<=this.dureeAnim){
							this.dureeAnim=0;
							cCharac.setAction(0);
							cCharac.setMoving(false);
						}
						else {this.dureeAnim++;cCharac.setAction(8);}
					}
				}
			}
			else {
				ordo.next();
				//this.addZombie();
				cCharac = null;
				for (DisplayCharacter c : characters) {
					if(c.getCharacter() == ordo.getCharacter()){
						cCharac = c;
					}
				}
				if(cCharac != null){
					this.currentChar = cCharac;
					cCharac.setMoving(true);
					switch (ordo.getDirection()){
					case 'U': break;
					case 'S': cCharac.setDirection(0); break;
					case 'O': cCharac.setDirection(1); break;
					case 'E': cCharac.setDirection(2); break;
					case 'N': cCharac.setDirection(3); break;
					default:;
					}
				}
			}

			this.gameOver = Moteur.clean_dead_bodies(this.charactersList) > 0 ;
			//			if(cCharac.getCharacter() == null){
			//				System.out.println("remove displaycharacter");
			//				this.characters.remove(cCharac);
			//			}
		}

		cleanCharacters();
		if(this.isMoving){
			switch(this.direction){
			case 0: if(this.mapOrigin.y > 0) this.mapOrigin.y--;break;
			case 1: if(this.mapOrigin.y < (map.getHeight()-screenHeight/TILED_SIZE)) this.mapOrigin.y++;break;
			case 2: if(this.mapOrigin.x > 0)this.mapOrigin.x--;break;
			case 3: if(this.mapOrigin.x< (map.getWidth()-screenWidth/TILED_SIZE)) this.mapOrigin.x++;break;

			}
		}

	}

	private void addZombie() throws SlickException {
		if(Math.random()*100 <= ordo.getTurn() && EcranDeValidation.mode > -3 && (ordo.get_remaining_zombies()) < 20){
			Zombie z = map.random_pop_zombie(charactersList, zombies);
			characters.add(new DisplayZombie(z));
		}

	}

/*
	public static void main(String[] args) throws SlickException {
		startgame();
		ArrayList<Character> lC = StateGame.loadCharacters(2) ; 
		Map carte = Moteur.initiate_map(lC, StateGame.getZombies());
		Ordonnanceur ordo = new Ordonnanceur(lC);
		WindowGame wg = new WindowGame();
		wg.initialisedGameModel(lC, carte,ordo);
		AppGameContainer tmp = new AppGameContainer(null);
		AppGameContainer app= new AppGameContainer(wg,tmp.getScreenWidth(),tmp.getScreenHeight(),false);
		wg.setScreenDimension(tmp.getScreenWidth(),tmp.getScreenHeight());
		System.out.println(wg.screenWidth+"/"+tmp.getScreenWidth()+" "+wg.screenHeight+"/"+app.getScreenHeight());
		app.start();*/
	//	}
}