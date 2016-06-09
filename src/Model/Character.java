/**
 * 
 */
package Model;

import java.awt.Point;
//import java.util.ArrayList;
import java.util.Observable;

/**
 * La classe abstraite Character représente chaque personnage present sur le jeu (survivant et zombies)
 * Elle regroupe les attributs et les fonctions communes aux survivants et aux zombies
 *
 */
public abstract class Character extends Observable {
	/**
	 * Attributs:
	 * hérités de Character
	 * int hp : les points de vie du survivant. Une fois à 0 il disparait du jeu. 
	 * Player player : le joueur qu'il représente
	 * int strength : sa force de combat
	 * Cell cell : la cellule sur le plateau qu'il occupe
	 * Automata automata: son automate soit son comportement dans le jeu
	 * Map map: le plateau/terrain de jeu
	 */
	//Attributs
	private int hp ;//points de vie 
	private Player player;
	private int strength ; 
	private Cell cell;
	private Automata automata;
	private Map map;
	
	/**
	 * Constructeur
	 * @param player 
	 * @param automata
	 * @param map
	 */
	public Character(Player player, Automata automata, Map map) {
		this.hp=100;
		this.strength=1;
		this.player=player;
		this.automata=automata;
		this.map=map;
	}
	
	public int getHp() {
		return hp;
	}
	public void supHp(int moins){
		this.hp=this.hp-moins;
		setChanged();
		notifyObservers(this.hp);
	}
	/*public void setHp(int hp) {
		this.hp = hp;
	}
*/
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
		setChanged();
		notifyObservers(this.player);
	}

	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
		setChanged();
		notifyObservers(this.strength);
	}

	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
		setChanged();
		notifyObservers(this.cell);
	}

	public Automata getAutomata() {
		return automata;
	}
	public void setAutomata(Automata automata) {
		this.automata = automata;
		setChanged();
		notifyObservers(this.automata);
	}

	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
		setChanged();
		notifyObservers(this.map);
	}

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
		case 'O': this.cell=map.getGrid()[x+1][y] ;break;
		case 'S': this.cell=map.getGrid()[x][y+1] ;break;
		case 'N': this.cell=map.getGrid()[x][y-1] ;break;
		case 'E': this.cell=map.getGrid()[x-1][y] ;break; 
		default : //throw new Require ("visible"); 
			     break;
		}	
	}
	public boolean is_alive () {
		if (hp > 0){
			return true;
		}
		else return false;
	}
	

}
