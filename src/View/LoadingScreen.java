package View;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingScreen extends BasicGameState {

	public static final int ID = 10;
	private String lastLoaded;
	private DeferredResource nextResource;
	private boolean started;
	
	 //ressource des menu de choix 
	public static Image background;
	public static Image Continue;
	public static Image Newgame;
	public static Image Option;
	public static Image souris; 
	public static Image souris2;
	public static Music musicmainscreen ;
	//Menu choix hu1 ou hu2 
	public static Image hu1 ;
	public static Image hu2;
	public static Image var ;
	 //Ecran de validation
	public static Image valider ;
	public static Image  oui ;
	public static  Image non;
	//fond dans credit
	public static Image credit;
	
	//music dans le jeu 
	public static Music musicgame;
	//pour la ifn du jeu 
	public static Image b42;
	public static Image bfin; 
	public static Music musicfin ;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		 LoadingList.setDeferredLoading(true);
		
		this.lastLoaded = "" ; 
		 //ressource des menu de choix 
		 this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		 this.Newgame = new Image ("../Zombautomate/ressources/Menu/newgame.png");
		 this.Continue = new Image ("../Zombautomate/ressources/Menu/continue.png");
		 this.Option = new Image ("../Zombautomate/ressources/Menu/exit.png"); 
		 this.souris = new Image ("../Zombautomate/ressources/Menu/UpArrow.png");
		 this.souris2 = new Image ("../Zombautomate/ressources/Menu/AppStarting2.png");
		 this.musicmainscreen = new Music("../Zombautomate/ressources/song/Menu2.ogg");
		 
		 //Menu choix hu1 ou hu2 
		 this.hu1 = new Image ("../Zombautomate/ressources/Menu/1vZombie.png");
		 this.hu2= new Image ("../Zombautomate/ressources/Menu/2vZombies.png");
		 this.var = new Image ("../Zombautomate/ressources/Menu/variante.png");
		 
		 //Ecran de validation
		 this.valider = new Image("../Zombautomate/ressources/Menu/valider.png");
			this.oui = new Image ("../Zombautomate/ressources/Menu/panneau-yes.png");
			this.non= new Image ("../Zombautomate/ressources/Menu/panneau-no.png");
		//Ecran de Credit 
		this.credit = new Image("../Zombautomate/ressources/Menu/backround_credit.png");
		
		//windows game 
		this.musicgame =  new Music("../Zombautomate/ressources/song/ingame2.ogg"); 
		
		//fin du jeu 
		this.b42 =  new Image("../Zombautomate/ressources/fondvictoire.png");
		this.bfin =   new Image("../Zombautomate/ressources/Menu/fin.png");
		this.musicfin =  new Music("../Zombautomate/ressources/song/endofgame2.ogg");
			
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (nextResource != null) {
			g.drawString("Loading: "+nextResource.getDescription(), 100, 100);
		}
		
		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
		
		float bar = loaded / (float) total;
//		g.setColor(Color.green);
		g.fillRect(100,150,loaded*40,20);
//		g.setColor(Color.red);
		g.drawRect(100,150,total*40,20);
		

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2)
			throws SlickException {
		
		if (nextResource != null) {
			try {
				nextResource.load();
				// slow down loading for example purposes
				try { Thread.sleep(50); } catch (Exception e) {}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			
			nextResource = null;
		}
		
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} 
		else {
			if (!started) {
				started = true;
				if	((System.getProperties().get("os.name")).equals("Linux") ) {
				game.enterState(MainScreenGameState.ID);
				}
				else {
					game.enterState(WindowGame.ID);
				}
			}
		}

	}

	@Override
	public int getID() {
		return ID;
	}

}
