package View;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import extension.BestScore;

public class EndGameView extends BasicGameState {
	public static final int ID = 6;
	
	private Image background42 ;
	private Image back;
	TrueTypeFont font;
	TrueTypeFont font2 ;
	public static Music music;
	private int pos ; 
	private ArrayList<Integer> listscore ; 

	private StateBasedGame game;

	
	
	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#enter(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.background42 = LoadingScreen.b42; new Image("../Zombautomate/ressources/fondvictoire.png");
		this.back = LoadingScreen.bfin;  new Image("../Zombautomate/ressources/Menu/fin.png");
		this.music = LoadingScreen.musicfin ; new Music("../Zombautomate/ressources/song/endofgame2.ogg");
		EndGameView.music.loop();
		BestScore BS = new BestScore();
		int nbJ = (WindowGame.ordo.getPlayer(2) == null)? 1:2 ;
		this.pos = BS.bestscoreplayer(WindowGame.ordo.getTurn(), nbJ);
		this.listscore = BS.read(nbJ);
		
		super.enter(container, game);
		
//		System.out.println("\n\n\n dans le déut de end game \n\n\n");
	}

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
		int nbZombie =0 , nbPerso1 =0 , nbPerso2=0 ; 
		back.draw(0, 0, container.getWidth(), container.getHeight());
		
		
		if(WindowGame.ordo.getPlayer(0)!=null){
			nbZombie = WindowGame.ordo.get_remaining_zombies() ;
		}		
		if(WindowGame.ordo.getPlayer(1)!=null){
			nbPerso1 = WindowGame.ordo.getPlayer(0).characters_remaining() ;
		}
		if(WindowGame.ordo.getPlayer(2)!=null){
			nbPerso2 = WindowGame.ordo.getPlayer(2).characters_remaining();
		}
		
		if(WindowGame.ordo.getTurn()==42){
			background42.draw(0, 0, container.getWidth(), container.getHeight());
			font2.drawString(larg/4    , haut/5  , "OMG VICTOIRE TOTALE 42 42 42" + WindowGame.ordo.getTurn() +" tours" , Color.orange);
		}
		else{
			if(EcranDeValidation.mode == 2 || EcranDeValidation.mode == 5){
				if (!Credit.J1.defeated()){
					font2.drawString(larg/4    , haut/5  , "Le vainquer est joueur 1" , Color.orange);

				}
				else{
					font2.drawString(larg/4    , haut/5  , "Le vainqueur est joueur 2" , Color.orange);
				}
				font.drawString(larg/6, haut/3 + 2*haut/10 , "Vous avez tenu " + WindowGame.ordo.getTurn() +" tours " , Color.orange);
			}
			else{
				font2.drawString(larg/4    , haut/5  , "Vous avez tenu " + WindowGame.ordo.getTurn() +" tours" , Color.orange);
			}
		}
		
		
		font.drawString(larg/6, haut/3 , "nombres de zombies sur la cartes : " + nbZombie  , Color.orange);
		font.drawString(larg/6, haut/3 +haut/10, "nombres personnages restant : " +(nbPerso1+nbPerso2) , Color.orange);
		if(pos!=0){
			font.drawString(larg/6, haut/3 + 3*haut/10, "Bravo, votre classement est : "+pos , Color.orange);
		}
		else{
			font.drawString(larg/6, haut/3 + 3*haut/10, "Tableau des scores : " , Color.orange);			
		}
		font.drawString(5*larg/8, haut/3 + 3*haut/10         ,  "Record     : "+listscore.get(0) , Color.orange);
		font.drawString(5*larg/8, haut/3 + 3*haut/10+ haut/25,  "Deuxieme   : "+listscore.get(1) , Color.orange);
		font.drawString(5*larg/8, haut/3 + 3*haut/10+ 2*haut/25,"Troisieme : "+listscore.get(2) , Color.orange);

		
		font.drawString(larg/24, haut - haut/5, " Taper R pour rejouer" , Color.white);
		font.drawString(larg/24, haut - haut/5+haut/30, " Taper echap pour quitter" , Color.white);
		font.drawString(larg/24, haut - haut/5 +haut/15, " Taper enter pour acceder au menu" , Color.white);	
		
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
		case Input.KEY_ENTER: game.enterState(MainScreenGameState.ID);
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
