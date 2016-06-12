/**
 * 
 */
package Model;

import java.awt.Point;

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
		
		
		//Constructeurs
		public Cell(Point pos){
			decor = this.randomDecor();
			entity_on=null;
			owned_by=null;
			position = pos;
		}
		
		public Cell(){}
		
		/**
		 * génère un décor aléatoire
		 * @return Decor aléatoire
		 */
		private Decor randomDecor()
		{
			switch((int) Math.random()*8)
			{
			case 0 : return Decor.BASEBALL_BAT;
			case 1 : return Decor.APPLE;
			case 2 : return Decor.GRASS;
			case 3 : return Decor.KATANA;
			case 4 : return Decor.RABBIT;
			case 5 : return Decor.ROCK;
			case 6 : return Decor.SPROUT;
			case 7 : 
			default :return Decor.TREE; 
			}
		}
		
		//Methodes
		//getter
		public Decor getDecor(){
			return this.decor;
		}
		public Character getEntity_on(){
			return this.entity_on;
		}
		public Character getOwned_by(){
			return this.owned_by;
		}
		public Point getPosition(){
			return this.position;
		}
		
		//setter
		public void setDecor(Decor decor){
			this.decor=decor;
			return;
		}
		public void setEntity_on(Character entity_on){
			this.entity_on=entity_on;
			return;
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
			if (this.owned_by!=null){
				Point poscell=this.position;
				Point posaut=this.owned_by.getAutomata().getPosition();
				int x= poscell.x-posaut.x;
				int y= poscell.y-posaut.y;
				this.owned_by.getAutomata().getStates()[x][y].setAction(this.decor.getAction());
			}
		}
}
