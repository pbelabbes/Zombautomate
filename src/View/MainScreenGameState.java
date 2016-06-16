package View;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState  {

	public static final int ID = 1;
	private Image background;
	private Image Continue;
	private Image Newgame;
	private Image Option;
	private Image souris; 
	private Image souris2;

	
	

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		
		this.Newgame = new Image ("../Zombautomate/ressources/Menu/newgame.png");
		this.Continue = new Image ("../Zombautomate/ressources/Menu/continue.png");
		this.Option = new Image ("../Zombautomate/ressources/Menu/exit.png"); 
		this.souris = new Image ("../Zombautomate/ressources/Menu/UpArrow.png");
		this.souris2 = new Image ("../Zombautomate/ressources/Menu/AppStarting2.png");
		container.setMouseCursor(this.souris, 0, 0);
	}
	
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		int larg = container.getWidth();
		int haut = container.getHeight() ;
		background.draw(0, 0, container.getWidth(), container.getHeight());
		//g.drawString("Appuyer sur une touche", 300, 300);
		Newgame.draw(larg/2 - larg/10 ,    haut/2 - haut/30 -haut/18 					, larg/5, haut/7);
		Continue.draw(larg/2 - larg/10 ,   haut/2 + haut/7 - haut/18 					, larg/5, haut/7);
		Option.draw(larg/2 - larg/10 ,     haut/2 + haut/7 + haut/7 + haut/30 - haut/18 , larg/5, haut/7);
		//Cont.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int PosX = Mouse.getX() ;
		int PosY = Mouse.getY() ;
		int larg = container.getWidth();
		int haut = container.getHeight() ;

		if ( (PosX> larg/2 - larg/10 )&& (PosX < larg/2 - larg/10 + larg/5 )  ){
			
			//bouton Newgame 
			if((PosY<haut - (haut/2 - haut/30 -haut/18 ) ) && (PosY>haut -( haut/2 - haut/30 -haut/18 + haut/7)  )){
				container.setMouseCursor(this.souris2, 0, 0);
				
				if(Mouse.isButtonDown(0)){
					game.enterState(MenuTypeJeu.ID);
				}
			}
//			container.setMouseCursor(this.souris, 0, 0);
			
			//bonton COntinue 
			if((PosY< haut - ( haut/2 + haut/7 - haut/18) ) && (PosY> haut - (haut/2 + haut/7 - haut/18 + haut/7 ) )){
				container.setMouseCursor(this.souris2, 0, 0);
				if(Mouse.isButtonDown(0)){
					game.enterState(ContinueMenutypeJeu.ID);
				}
			}
//			container.setMouseCursor(this.souris, 0, 0);
			
			//Bouton Option
			if((PosY< haut -( haut/2 + haut/7 + haut/7 + haut/30 - haut/18) ) && (PosY> haut -( haut/2 + 3*haut/7  + haut/30 - haut/18 )  )){
				container.setMouseCursor(this.souris2, 0, 0);
				if(Mouse.isButtonDown(0)){
					System.exit(0) ;
				}
			}
//			container.setMouseCursor(this.souris, 0, 0);
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}


	

}
