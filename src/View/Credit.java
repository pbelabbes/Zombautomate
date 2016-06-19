package View;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Model.Player;

public class Credit extends BasicGameState {
	public static final int ID = 5;
	private Image background ; 
	private StateBasedGame game ; 
	TrueTypeFont font;
	TrueTypeFont font2 ;
	public static Player J1 ;
	public static Player J2 ;
	private int creditbase = 300 ; 
	private int creditj2 ;
	
	
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.background = LoadingScreen.credit;
		super.enter(container, game);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game ; 
		
		Font awtFont = new Font("Verdane",  Font.BOLD, 24);
		font =  new TrueTypeFont(awtFont, false);
		font2 = new TrueTypeFont(new Font("Verdane",  Font.BOLD, 90), false);
		
		J2 = null;
		this.creditj2 = creditbase;

		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		int larg = container.getWidth()  ;
		int haut = container.getHeight() ;
//		int creditj2 = creditbase ;
//		g.setColor(Color.black);
//		g.setFont(font);
		
		background.draw(0, 0, container.getWidth(), container.getHeight()-55);
		
		font.drawString(larg/10 + larg/30 - larg/20, haut/16 - haut/60 , "Joueur1 : Credit restant" , Color.orange);
		
		font.drawString(larg/2, haut/2 +haut/30, "Joueur2 : Credit restant" , Color.orange);
		
		if (J2 != null){
			creditj2 = creditbase- J2.getCost() ;
		}
		font2.drawString(larg/2 + larg/10 - haut/15 +3, haut/2 +haut/30 + haut/5 - haut/15 -3 , Integer.toString(creditj2) , Color.black);
		font2.drawString(larg/2 + larg/10 - haut/15, haut/2 +haut/30 + haut/5 - haut/15, Integer.toString( creditj2) , Color.orange);

		font2.drawString(larg/4 - larg/10 +3, haut/4 - haut/13 -3 , Integer.toString(creditbase -J1.getCost()) , Color.black);
		font2.drawString(larg/4 - larg/10   , haut/4 -haut/13 , Integer.toString( creditbase -J1.getCost()) , Color.orange);
	
		if (creditbase -J1.getCost()> 0 ){
			font.drawString(larg/10 + larg/30 , haut/4 + haut/10, "Vous pouvez jouer" , Color.green);
		}
		else{
			font.drawString(larg/10 + larg/30 , haut/4 + haut/10 , "L'automate est trop grand" , Color.red);
		}
		
		if (creditj2>0 ){
			font.drawString(larg/2 + larg/20 , haut/2 +haut/15 + haut/4 , "Vous pouvez jouer" , Color.green);
		}
		else{
			font.drawString(larg/2 + larg/40 , haut/2 +haut/15 + haut/4 ,"L'automate est trop grand" , Color.red);
		}
		
		if(creditj2 > 0 && creditbase -J1.getCost()> 0  ){
			g.drawString("appuyer sur une touche pour jouer" , larg/8, 3*haut/4);
		}
		else{
			g.drawString("appuyer sur une touche editer vos automates" , larg/8, 3*haut/4);
		}
	
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		

	}
	
	 public void keyReleased(int key, char c) {
		    //game.enterState(MapGameState.ID);
		 if(creditj2 > 0 && creditbase -J1.getCost()> 0  ){
			 game.enterState(WindowGame.ID);
			 
			 
			 
		 }
		 else {
			 if(EcranDeValidation.mode == 1 || EcranDeValidation.mode == 2 ){
				 EcranDeValidation.mode += 3;
				 System.out.println("\n\n\n\n"+EcranDeValidation.mode+"\n\n\n\n"); 
			 }
			 game.enterState(EcranDeValidation.ID);
		 }
	 }

//	@Override
//	public void leave(GameContainer container, StateBasedGame game)
//			throws SlickException {
//		if (game. == WindowGame.ID){
//			MainScreenGameState.music.stop() ;
//			WindowGame.music.loop();
//		}
//		super.leave(container, game);
//	}

	@Override
	public int getID() {
		return ID;
	}

}
