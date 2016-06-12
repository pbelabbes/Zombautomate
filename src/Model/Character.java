/**
 * 
 */
package Model;
import java.util.ArrayList;
import java.awt.Point;
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
	 * int state: l'etat dans lequel se trouve le caractère. 
	 */
	//Attributs
	private int hp ;//points de vie
	private int sight_range; //portée de vision
	private Player player;
	private int strength ; 
	private Cell cell;
	private Automata automata;
	private Map map;
	private int state;
	
	/**
	 * Constructeur
	 * @param player 
	 * @param automata
	 * @param map
	 */
//	public Character(Player player, Automata automata) {
//		this.hp=100;
//		this.strength=1;
//		this.player=player;
//		this.automata=automata;
//		this.sight_range = 2;
//	}
	
	public Character(Player player, Automata automata, Map map) {
		this.hp=100;
		this.strength=1;
		this.player=player;
		this.automata=automata;
		this.map=map;
		this.sight_range = 2;
		this.state = 0;
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

	public int getSightRange()
	{
		return this.sight_range;
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
	
	// Verifie si un personnage est vivant
	public boolean is_alive () 
	{
		return hp>0;
	}
	
	
	//Fait faire sa prochaine action a un personnage
	/**
	 * 
	 */
	public void play (){
		
		ArrayList<CaseAutomate> List_cases = new ArrayList<CaseAutomate>();
		int i=0;
		int j=0;
		CaseAutomate [][] cA = automata.getStates();
		
		//recupère la condition de transition de l'état courant puis ajoute dans une liste les case de l'automate avec une transition possible
		automata.getEtatCourant();
		while (cA[i][j] != null){
			while (cA[i][j] != null){
				if (cA[i][j].getCondition().execute(this.getCell())){
					List_cases.add(cA [i][j]);
					i++;
				}
			}
		i = 0;
		j++;
		}
		//recupère dans la liste, la case avec la plus grande priorité et effectue l'action associé
		int k = 1;
		int cle = 0;
		while ( k!= List_cases.size()){
			if(List_cases.get(cle).getPriorite()> List_cases.get(k).getPriorite()){
				k++;
			}
			else { 
				cle = k;
				k++;
			}
		}	
		List_cases.get(cle).getAction();
		state = (List_cases.get(cle)).getEtatfutur();
	}


	/**
	 * @return nom du joueur auquel appartient le personnage
	 */
	public String toString()
	{
		return this.getPlayer().getName();
	}


}


