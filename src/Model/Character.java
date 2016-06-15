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
	protected int hp ;//points de vie
	protected int sight_range; //portée de vision
	protected Player player;
	protected int strength ; 
	protected Cell cell;
	protected Automata automata;
	protected Map map;
	protected int state;
	
	/**
	 * Constructeur
	 * @param player 
	 * @param automata
	 * @param map
	 */
	
	public Character(Player player, Automata automata, Map map) {
		this.hp=10;
		this.strength=1;
		this.player=player;
		this.automata=automata;
		this.map=map;
		this.sight_range = 20;
		this.state = 0;
	}
	
	/**
	 * execute l'action indiquée par le type Action dans la direction donnée
	 * @param action
	 * @param direction
	 */
	public abstract void act(Action action, char direction);

	
	public int getHp() {
		return hp;
	}
	
	/**
	 * Retire de la vie au personnage
	 * @param moins : Montant de vie retiré au personnage
	 */
	public void supHp(int moins){
		this.hp=this.hp-moins;
		if(!is_alive())
			cell.setEntity_on(null);
		
		setChanged();
		notifyObservers(this.hp);
	}

	
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
	public void deplacer (Cell cellule) {

		if(cellule.getEntity_on() == null && cellule.getDecor() != Decor.ROCK)
		{
			this.cell.setEntity_on(null);
			this.cell = cellule;
			cellule.setEntity_on(this);
		}
	}
	
	/**
	 * la fonction is_alive verifie si un personnage est vivant
	 * @return true si un personnage est vivant
	 */
	public boolean is_alive () 
	{
		return hp>0;
	}

	public abstract void eat();
	
	/**
	 * La fonction play fait faire sa prochaine action à un personnage
	 * Elle récupère la condition de l'état courant puis vérifie quelles sont les transitions possibles
	 * Elle choisi la transition en fonction des priorité puis effectue l'action associée à la transition
	 */
	public void play (){
		
		ArrayList<CaseAutomate> List_cases = new ArrayList<CaseAutomate>();
		int j=0;
		CaseAutomate [][] cA = automata.getStates();
		
		while ( j < this.automata.getInputs() && cA[state][j] != null)
		{
			if (cA[state][j].getCondition().execute(this.getCell()))
			{
				List_cases.add(cA [state][j]);
			}
			j++;
		}

		if(List_cases.size()==0) return;

		int k = 1;
		int cle = 0;
		if(List_cases.size()>1)
		{
			while ( k != List_cases.size()){
				if(List_cases.get(cle).getPriorite()> List_cases.get(k).getPriorite()){
					k++;
				}
				else { 
					cle = k;
					k++;
				}
			}	
		}
     if(List_cases.get(cle).getCondition() instanceof ScanLoin)	System.out.println(((ScanLoin) (List_cases.get(cle).getCondition())).getParameter());
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
	public void attaquer(Cell cellule){
System.out.println("J'attaque, nom de Dieu !");
		
		if (cellule.getEntity_on()!=null){
			//On enlève des points de vie à l'adversaire
			cellule.getEntity_on().supHp(this.getStrength());
		}
		else 
		{
			if (cellule.getDecor()==Decor.ROCK){
				int r= (int)(Math.random()*10);
				if (r<2)		cellule.setDecor(Decor.RABBIT);
				else if (r<4)	cellule.setDecor(Decor.KATANA);
				else			cellule.setDecor(Decor.GRASS);

			}
			else if(cellule.getDecor()==Decor.TREE)
			{
				int r = (int) (Math.random()*10);
				if(r<5)			cellule.setDecor(Decor.APPLE);
				else if(r<8)	cellule.setDecor(Decor.BASEBALL_BAT);
				else			cellule.setDecor(Decor.GRASS);

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
	
	/**
	 * cette fonction renvoie la cellule qui est a une direction 
	 * direction de la cellule passé en parametre
	 * @param direction
	 * @param cellule
	 * @return
	 */
	protected Cell getTargetedCell(char direction, Cell cellule )
	{
		Point p = new Point(cellule.getPosition());
		int mapheight = this.map.getHeight();
		int mapwidth = this.map.getWidth();
		switch(direction)
		{
		case 'N' : p.y=(p.y-1+mapheight)%mapheight; break;
		case 'E' : p.x=(p.x+1+mapwidth)%mapwidth; break;
		case 'S' : p.y=(p.y+1+mapheight)%mapheight; break;
		default : p.x=(p.x-1+mapwidth)%mapwidth; break;
		
		}
		return this.map.getGrid()[p.x][p.y];
	}

/**
 * Cette fonction sert a afficher les caracteristiques d'un personnage
 */
	public void showstat(){
		System.out.println("\thp = "+Integer.toString(hp) );//points de vie
		System.out.println("\tsight range  = "+ Integer.toString(sight_range)); //portée de vision
		
		System.out.println("\tstrength = "+Integer.toString( strength)) ; 
		System.out.println("\tposition x= "+Integer.toString( (int) cell.getPosition().getX() ) + " y = "+Integer.toString( (int) cell.getPosition().getY() )) ;  ;
		
	}
	/**
	 * cette fonction permet d'afficher le decor des cellules adjacentes 
	 */
	public void showaround(){
		int x,y;
		x=this.cell.getPosition().x;
		y=this.cell.getPosition().y;
		Cell[][] tab = this.map.getGrid() ;
		int mapheight = this.map.getHeight();
		int mapwidth = this.map.getWidth();
		System.out.println("Cell N = " + tab[x][(y-1+mapheight)%mapheight].getDecor());
		System.out.println("Cell E = " + tab[(x+1+mapwidth)%mapwidth][y].getDecor());
		System.out.println("Cell S = " + tab[x][(y+1+mapheight)%mapheight].getDecor());
		System.out.println("Cell O = " + tab[(x-1+mapwidth)%mapwidth][y].getDecor());
	}



}


