package View;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndGameView extends BasicGameState {
	public static final int ID = 6;
	
	private Image background ;
	TrueTypeFont font;
	TrueTypeFont font2 ;

	private StateBasedGame game;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		Font awtFont = new Font("Verdane",  Font.BOLD, 24);
		font =  new TrueTypeFont(awtFont, false);
		font2 = new TrueTypeFont(new Font("Verdane",  Font.BOLD, 40), false);	
		int larg = container.getWidth()  ;
		int haut = container.getHeight() ;
		this.game = game ; 
	
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics arg2)
			throws SlickException {
		int larg = container.getWidth()  ;
		int haut = container.getHeight() ;

		
		if(EcranDeValidation.mode == 2 || EcranDeValidation.mode == 5){
			if (Credit.J1.defeated()){
				font2.drawString(larg/4    , haut/5  , "Le vainquer est jouer1 " , Color.orange);

			}
			else{
				font2.drawString(larg/4    , haut/5  , "Le vainquer est jouer2" , Color.orange);
			}
			font.drawString(larg/6, haut/3 , "Vous avez tenu " +WindowGame.ordo.getTurn() +" tours " , Color.orange);
		}
		else
		font2.drawString(larg/4    , haut/5  , "Vous avez tenu " +WindowGame.ordo.getTurn() +" tours" , Color.orange);
		
		
		
//		font2.drawString(larg/3    , haut/4  , "GAME OVER" , Color.orange);
		
//		font.drawString(larg/6, haut/3 , "nombres joeurs restant : " , Color.orange);
		
		font.drawString(larg/6, haut/3+haut/10 , "nombres de zombies sur la cartes : " , Color.orange);
		font.drawString(larg/6, haut/3 + haut/5, "nombres personnages restant : " , Color.orange);
		font.drawString(larg/6, haut/3 + 3*haut/10, " " , Color.orange);
	}
	
	



public void keyReleased(int key, char c){
		
		
		switch (key) {
		//Mouvement personnage
		case Input.KEY_ESCAPE:  System.exit(0);   break;
		case Input.KEY_R:  
			if(EcranDeValidation.mode == 1 || EcranDeValidation.mode == 2 ){
				EcranDeValidation.mode += 3;
				System.out.println("\n\n\n\n"+EcranDeValidation.mode+"\n\n\n\n"); 
			}
			game.enterState(EcranDeValidation.ID); 
			break ;
//		case Input.KEY_3:  EcranDeValidation.mode= -3 ; game.enterState(EcranDeValidation.ID);break;
		}
	}
	
	
	@Override
	public void update(GameContainer acontainer, StateBasedGame game, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
