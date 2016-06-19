package View;

import java.util.concurrent.TimeUnit;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ContinueMenutypeJeu extends BasicGameState {

	public static final int ID = 3;
	private Image background;
	private Image hu1;
	private Image hu2;
	private Image var;
	private boolean released  ;
	private StateBasedGame game;
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.background = LoadingScreen.background;
		this.hu1 = LoadingScreen.hu1;
		 hu1.setColor(0, 1, 1, 1, 0);
		 this.hu2= LoadingScreen.hu2;
		 hu2.setColor(0, 1, 1, 1, 0);
		 this.var = LoadingScreen.var;
		 var.setColor(0, 1, 1, 1, 0);
		super.enter(container, game);
	}

	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.released = false ;

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		background.draw(0, 0, container.getWidth(), container.getHeight());
		hu1.draw(larg/2 - larg/10 ,    haut/2 - haut/30 -haut/18 					, larg/5, haut/7);
		hu2.draw(larg/2 - larg/10 ,   haut/2 + haut/7 - haut/18 					, larg/5, haut/7);
		var.draw(larg/2 - larg/10 ,     haut/2 + haut/7 + haut/7 + haut/30 - haut/18 , larg/5, haut/7);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int PosX = Mouse.getX() ;
		int PosY = Mouse.getY() ;
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		
		
		if(!Mouse.isButtonDown(0)){
			this.released = true;
		}
		//bouton 1 v 1 zombie
		if(MainScreenGameState.pos_valide_x(PosX,larg) && MainScreenGameState.pos_valide_y_1(PosY,haut))
		{			
			hu1 = new Image ("../Zombautomate/ressources/Menu/1vZombie.png");
			if(released  && Mouse.isButtonDown(0)){
				EcranDeValidation.mode = 4;
				game.enterState(EcranDeValidation.ID);

			}
		}
		else hu1.setColor(0, 1, 1, 1, 0);

		//bonton 2 v 1 Zombie
		if(MainScreenGameState.pos_valide_x(PosX,larg) && MainScreenGameState.pos_valide_y_2(PosY,haut))
		{
			hu2 = new Image ("../Zombautomate/ressources/Menu/2vZombies.png");
			if(released  && Mouse.isButtonDown(0)){
				EcranDeValidation.mode = 5;
				game.enterState(EcranDeValidation.ID);
			}
		}
		else hu2.setColor(0, 1, 1, 1, 0);

		//Bouton var
		if(MainScreenGameState.pos_valide_x(PosX,larg) && MainScreenGameState.pos_valide_y_3(PosY,haut))
		{
			this.var = new Image ("../Zombautomate/ressources/Menu/variante.png");
			if(released  && Mouse.isButtonDown(0)){
				//game.enterState(EcranDeValidation.ID);

			}
		}
		else var.setColor(0, 1, 1, 1, 0);
	}	

		
	


	@Override
	public int getID() {
		return ID;
	}

}
