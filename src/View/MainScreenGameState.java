package View;




import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background;
	private Image Continue;
	private Image Newgame;
	private Image Option;
	private StateBasedGame game;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		this.Newgame = new Image ("../Zombautomate/ressources/Menu/Newgame.png");
		this.Continue = new Image ("../Zombautomate/ressources/Menu/Continue.png");
		this.Option = new Image ("../Zombautomate/ressources/Menu/Option.png");
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
				System.out.println("\n\nPOSXinf =" + Integer.toString( larg/2 - larg/10) + "POSYinf = "+Integer.toString(haut/2 - haut/30 -haut/18));
				System.out.println("POSXsup =" + Integer.toString( larg/2 - larg/10 + larg/5) + "POSYsup = "+Integer.toString(haut/2 - haut/30 -haut/18 + haut/7));
				System.out.println("POSX =" + Integer.toString(PosX) + "POSY = "+Integer.toString(PosY));
				if(Mouse.isButtonDown(0)){
					game.enterState(MenuTypeJeu.ID);

				}
			}
			
			//bonton COntinue 
			if((PosY< haut - ( haut/2 + haut/7 - haut/18) ) && (PosY> haut - (haut/2 + haut/7 - haut/18 + haut/7 ) )){
				if(Mouse.isButtonDown(0)){
					//game.enterState(MenuTypeJeu.ID);
					System.exit(0) ;
					 
				}
			}
			
			//Bouton Option
			if((PosY< haut -( haut/2 + haut/7 + haut/7 + haut/30 - haut/18) ) && (PosY> haut -( haut/2 + 3*haut/7  + haut/30 - haut/18 )  )){
				if(Mouse.isButtonDown(0)){
					//game.enterState(MenuTypeJeu.ID);
					
				}
			}
		}
		
		
		
		
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	/*public void keyReleased(int key, char c) {
	    game.enterState(MenuTypeJeu.ID);
	    
	  }*/

	

}
