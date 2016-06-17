package View;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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

	private StateBasedGame game ; 
	

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.background = new Image("../Zombautomate/ressources/Menu/background.png");
		
		this.Newgame = new Image ("../Zombautomate/ressources/Menu/newgame.png");
//		Newgame.setImageColor(0,120,10,20);
		Newgame.setColor(0,1,1,1,0);
		
		this.Continue = new Image ("../Zombautomate/ressources/Menu/continue.png");
		Continue.setColor(0,1,1,1,0);

		this.Option = new Image ("../Zombautomate/ressources/Menu/exit.png"); 
		Option.setColor(0,1,1,1,0);

		this.souris = new Image ("../Zombautomate/ressources/Menu/UpArrow.png");
		this.souris2 = new Image ("../Zombautomate/ressources/Menu/AppStarting2.png");
		container.setMouseCursor(this.souris, 0, 0);
		this.game = game; 
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
	/**
	 * vérifie si la position de la souris correspond aux boutons du menu principal sur l'axe des x
	 * @param PosX coordonnee horizontale de la souris
	 * @param larg largeur de la fenetre
	 * @return true si la souris est bien positionnée
	 */
	public static boolean pos_valide_x(int PosX, int larg)
	{
		return (PosX> larg/2 - larg/10 )&& (PosX < larg/2 - larg/10 + larg/5 );
	}
	
	/**
	 * vérifie si la souris est à la hauteur du bouton (1) : New GAME
	 * @param PosY coordonee verticale de la souris
	 * @param haut hauteur de la fenetre
	 * @return true si la souris est bien placée
	 */
	public static boolean pos_valide_y_1(int PosY, int haut)
	{
		return PosY<haut - (haut/2 - haut/30 -haut/18)  && PosY>haut -( haut/2 - haut/30 -haut/18 + haut/7) ;
	}

	/**
	 * vérifie si la souris est à la hauteur du bouton (2) Continue
	 * @param PosY coordonee verticale de la souris
	 * @param haut hauteur de la fenetre
	 * @return true si la souris est bien placée
	 */
	public static boolean pos_valide_y_2(int PosY, int haut)
	{
		return PosY< haut - ( haut/2 + haut/7 - haut/18) && PosY> haut - (haut/2 + haut/7 - haut/18 + haut/7 );
	}
	
	/**
	 * vérifie si la souris est à la hauteur du bouton (3) EXIT
	 * @param PosY coordonee verticale de la souris
	 * @param haut hauteur de la fenetre
	 * @return true si la souris est bien placée
	 */
	public static boolean pos_valide_y_3(int PosY, int haut)
	{
		return PosY< haut -( haut/2 + haut/7 + haut/7 + haut/30 - haut/18)  && PosY> haut -( haut/2 + 3*haut/7  + haut/30 - haut/18);
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		int PosX = Mouse.getX() ;
		int PosY = Mouse.getY() ;
		int larg = container.getWidth();
		int haut = container.getHeight() ;

		//bouton Newgame 
		if(pos_valide_y_1(PosY,haut) && pos_valide_x(PosX,larg))
		{	
			Newgame =  new Image ("../Zombautomate/ressources/Menu/newgame.png");
			container.setMouseCursor(this.souris2, 0, 0);
			if(Mouse.isButtonDown(0)){
				game.enterState(MenuTypeJeu.ID);
			}
		}
		else Newgame.setColor(0, 1, 1, 1, 0);

		//			container.setMouseCursor(this.souris, 0, 0);

		//bonton COntinue 
		if(pos_valide_y_2(PosY, haut) && pos_valide_x(PosX,larg))
		{
			this.Continue = new Image ("../Zombautomate/ressources/Menu/continue.png");
			container.setMouseCursor(this.souris2, 0, 0);
			if(Mouse.isButtonDown(0)){
				game.enterState(ContinueMenutypeJeu.ID);
//				game.enterState(WindowGame.ID);
			}
		}
		else Continue.setColor(0, 1, 1, 1, 0);
		//			container.setMouseCursor(this.souris, 0, 0);

		//Bouton Exit (Option)
		if(pos_valide_y_3(PosY, haut) && pos_valide_x(PosX,larg))
		{			
			this.Option = new Image ("../Zombautomate/ressources/Menu/exit.png"); 
			container.setMouseCursor(this.souris2, 0, 0);
			if(Mouse.isButtonDown(0)){
				System.exit(0) ;
			}
		}
		else Option.setColor(0, 1, 1, 1, 0);
		//			container.setMouseCursor(this.souris, 0, 0);

	}
	
	
	@Override
	public void keyPressed(int key, char c) {
		
		switch (key) {
		//Mouvement personnage
		case Input.KEY_1:  EcranDeValidation.mode= -1 ; game.enterState(EcranDeValidation.ID);   break;
		case Input.KEY_2:  EcranDeValidation.mode= -2 ; game.enterState(EcranDeValidation.ID); break ;
		case Input.KEY_3:  EcranDeValidation.mode= -3 ; game.enterState(EcranDeValidation.ID);break;
		}
	}
	
	

	@Override
	public int getID() {
		return ID;
	}


	

}
