/**
 * 
 */
package Model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * La classe abstraite Character représente chaque personnage present sur le jeu (survivant et zombies)
 * Elle regroupe les attributs et les fonctions communes aux survivants et aux zombies
 *
 */
public abstract class Character {
	/**
	 * Attributs:
	 * hérités de Character
	 * int hp : les points de vie du survivant. Une fois à 0 il disparait du jeu. 
	 * Player player : le joueur qu'il représente
	 * int strength : sa force de combat
	 * Cell cell : la cellule sur le plateau qu'il occupe
	 * Automata automata: son automate soit son comportement dans le jeu
	 * Map map: le plateau/terrain de jeu
	 * 
	 */
	//Attributs
	public int hp ;//points de vie 
	public Player player;
	public int strength ; 
	public Cell cell;
	public Automata automata;
	public Map map;
	
	
	//Methodes
	/**
	 * La fonction deplacer permet de déplacer le personnage dans une direction (Nord, Sud, Est, Ouest)
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action.
	 */
	public void deplacer (char direction) {
		int x,y;
		x=this.cell.getPosition().x;
		y=this.cell.getPosition().y;
		switch (direction){
		//on modifie la position et donc la cellule du personnage
		case 'O': this.cell=map.grid[x+1][y] ;break;
		case 'S': this.cell=map.grid[x][y+1] ;break;
		case 'N': this.cell=map.grid[x][y-1] ;break;
		case 'E': this.cell=map.grid[x-1][y] ;break; 
		default : //throw new Require ("visible"); 
			     break;
		}	
	}
	
	/**
	 * La fonction attaquer porte un coup vers la case indiquée
	 * Si un ennemi est present sur cette case, il perd des points de vie
	 * Si il y a un rock sur cette case elle se casse et on découvre soit un katana soit un lapin à sa place
	 * Sinon rien ne se passe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void attaquer(char direction){
		Point p=new Point(this.cell.getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		
		if (map.grid[p.x][p.y].getEntity_on()!=null){
			//On enlève des points de vie à l'adversaire
			map.grid[p.x][p.y].getEntity_on().hp--;
		}
		else {
			if (map.grid[p.x][p.y].getDecor()==Decor.ROCK){
				int r= (int)(Math.random()*.2);
				if (r==0){
					map.grid[p.x][p.y].setDecor(Decor.RABBIT);
				}
				else{
					map.grid[p.x][p.y].setDecor(Decor.KATANA);
				}
			}
		}
	}
	
	
	

}
