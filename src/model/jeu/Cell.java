/**
 * 
 */
package model.jeu;
import java.awt.Point;

import java.util.Random;
import model.decors.*;
import model.automate.*;

/**
 * La classe cellule représente une case du plateau de jeu
 *
 */
public class Cell {

	/**
	 * Attributs:
	 * Decor decor: il s'agit du decor de la case
	 * Character entity_on: si un character est present sur cette case, c'est la valeur de celui-ci sinon null
	 * Character owned_by: si la case influence l'automate d'un character elle a sa valeur sinon null
	 * Point position : la position de la cellule sur la map (=le plateau de jeu) 
	 */
		private Decor decor;
		private Character entity_on;
		private Character owned_by;
		private Point position;
		private CaseAutomate case_automate;
		
		//Constructeurs
		public Cell(Point pos){
			decor = this.randomDecor();
			entity_on=null;
			owned_by=null;
			position = pos;
		}
		
		
		/**
		 * Cette fonction permet de renvoyer un double au hasard 
		 * @param seed
		 * @return
		 */
		public static double randomGenerator(long seed) {
		    Random generator = new Random(seed);
		    double num = generator.nextDouble() * (0.5);
		    return num;
		}
		
		/**
		 * génère un décor aléatoire
		 * @return Decor aléatoire
		 */
		private Decor randomDecor()
		{
			switch((int) (40*Math.random()))
			{
			case 0 : return Decor.BASEBALL_BAT;
			case 1 : return Decor.APPLE;
			case 2 : return Decor.TREE;
			case 3 : return Decor.KATANA;
			case 4 : return Decor.RABBIT;
			case 5 : return Decor.ROCK;
			case 6 : return Decor.SPROUT;
			case 7 : 
			default :return Decor.GRASS; 
			}
		}
		
		//Methodes
		//getter
		public Decor getDecor(){
			return this.decor;
		}

		/**
		 * 
		 * @return case d'un automate relié à cette cellule
		 */
		public CaseAutomate getCaseAutomate()
		{
			return case_automate;
		}
		
		
		/**
		 * @return personnage présent sur la cellule 
		 */
		public Character getEntity_on(){
			return this.entity_on;
		}
		
		/**
		 * 
		 * @return personnage dont l'automate est relié à cette case
		 */
		public Character getOwned_by(){
			return this.owned_by;
		}
		
		/**
		 * @return position de la cellule sur la map
		 */
		public Point getPosition(){
			return this.position;
		}
		
		
		//setter
		public void setDecor(Decor decor){
			this.decor=decor;
			return;
		}
		
		public void setCaseAutomate(CaseAutomate cA)
		{
			case_automate = cA;
		}
		
		/**
		 * indique à la cellule et à l'entité qu'ils sont reliés entre eux
		 * @param entity_on
		 */
		public void setEntity_on(Character entity_on){
			this.entity_on=entity_on;
			if(entity_on!=null) entity_on.setCell(this);
		}
		
		public void setOwned_by(Character owned_by){
			this.owned_by=owned_by;
			return;
		}
		public void setPosition(Point position){
			this.position=position;
			return;
		}
		
		
		//Méthodes
		/**
		 * La fonction majAutomate permet de mettre à jour l'automate du joueur si la cellule appartient à son automate
		 * Elle change uniquement l'action effectuée par le personnage dans son automate.
		 */
		
		public void majAutomate(){
			if (this.case_automate != null)
			{
				
				case_automate.setAction(decor.getAction());
				
//				Point poscell = this.position;
//				Point posaut = this.owned_by.getAutomata().getPosition();
//				int x = poscell.x-posaut.x;
//				int y = poscell.y - posaut.y ;
//				CaseAutomate cA = this.owned_by.getAutomata().getStates()[x][y];
//				if(cA != null) cA.setAction(this.decor.getAction());
			}
		}
		
		public void print_cell()
		{
			if(this.entity_on == null)
			{
				if(this.decor == null)  System.out.print("error");
				switch(this.decor)
				{
				case GRASS : System.out.print(" "); break;
				case ROCK : System.out.print("*"); break;
				case TREE : System.out.print("8"); break;
				default  : System.out.print("x");
				
				}
			}
			else System.out.print(entity_on.getPlayer().getId());
		}
}
