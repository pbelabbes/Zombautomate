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
//		int i=0;
		int j=0;
		CaseAutomate [][] cA = automata.getStates();
		
		//recupère la condition de transition de l'état courant puis ajoute dans une liste les case de l'automate avec une transition possible
//		int etat_courant = automata.getEtatCourant();
		while (cA[state][j] != null)
		{
			if (cA[state][j].getCondition().execute(this.getCell()))
			{
				List_cases.add(cA [state][j]);
//				i++;
			}

//			i = 0;
			j++;
		}
		
		
		
		//recupère dans la liste, la case avec la plus grande priorité et effectue l'action associée
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
		Action act = List_cases.get(cle).getAction();
		char dir = List_cases.get(cle).getDirection();
		this.act(act,dir);  //faire une fonction qui fait l'action indiquée par le contenu de la case
		state = (List_cases.get(cle)).getEtatfutur();
	}


	
	
	/**
	 * La fonction attaquer porte un coup vers la case indiquée
	 * Si un ennemi est present sur cette case, il perd des points de vie
	 * Si il y a un rock sur cette case elle se casse et on découvre soit un katana soit un lapin à sa place
	 * Sinon rien ne se passe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void attaquer(char direction){
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		
		if (getMap().getGrid()[p.x][p.y].getEntity_on()!=null){
			//On enlève des points de vie à l'adversaire
			getMap().getGrid()[p.x][p.y].getEntity_on().supHp(this.getStrength());
		}
		else {
			if (getMap().getGrid()[p.x][p.y].getDecor()==Decor.ROCK){
				int r= (int)(Math.random()*.10);
				if (r<2)		getMap().getGrid()[p.x][p.y].setDecor(Decor.RABBIT);
				else if (r<4)	getMap().getGrid()[p.x][p.y].setDecor(Decor.KATANA);
				else			getMap().getGrid()[p.x][p.y].setDecor(Decor.GRASS);

			}
			else if(getMap().getGrid()[p.x][p.y].getDecor()==Decor.TREE)
			{
				int r = (int) (Math.random()*10);
				if(r<5)			getMap().getGrid()[p.x][p.y].setDecor(Decor.APPLE);
				else if(r<8)	getMap().getGrid()[p.x][p.y].setDecor(Decor.BASEBALL_BAT);
				else			getMap().getGrid()[p.x][p.y].setDecor(Decor.GRASS);

			}
		}
	}
	
	
	
	/**
	 * @return nom du joueur auquel appartient le personnage
	 */
	public String toString()
	{
		return this.getPlayer().getName();
	}

	public abstract void act(Action action, char direction);

}


