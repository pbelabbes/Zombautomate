package Model;
import java.awt.Point;

//import jus.util.assertion.Require;

import java.awt.Point;
import java.util.ArrayList;


/**
 * Cette classe représente un personnage non zombie qui sera placé sur le terrain de jeu
 * Elle hérite de Character

 *
 */
public class Survivor extends Character{

	//Attributs
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
	 * propre:
	 * ArrayList<Arme> arme: la liste de ses armes.
	 */
	
	private ArrayList<Arme> weapon;
	
	public ArrayList<Arme> getWeapon() {
		return weapon;
	}

	public void setWeapon(ArrayList<Arme> weapon) {
		this.weapon = weapon;
	}

	//constructeurs
	/**
	 * Constructeur d'un survivant:
	 * il part avec 100 points de vie
	 * sa force est de ??
	 * 
	 */
	public Survivor(Player player, Automata automata, Map map) {
		super(player, automata, map);
		this.weapon=null;
	}

	//Méthodes
	
	
	
	/**
	 * La fonction drop permet de poser une pierre sur de l'herbe
	 * Elle ne fait rien si le decor de la case indiquée ne correspond pas à de l'herbe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void drop (char direction){
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if (getMap().grid[p.x][p.y].getDecor()==Decor.GRASS){
			getMap().grid[p.x][p.y].setDecor(Decor.ROCK);
		}				
	}
	
	
	/**
	 * La fonction pick permet de récupérer de la nourriture ou des armes posées sur le sol
	 * Elle met à jour les données du joueur et du personnage
	 * Elle ne fait aucune action si la case indiquée ne correspond pas au décor de nourriture ou d'arme
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void pick(char direction){
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		switch (this.getMap().grid[p.x][p.y-1].getDecor()){
		case APPLE: this.getPlayer().foodStock=this.getPlayer().foodStock+ Nourriture.APPLE.getvalues();break;
		case RABBIT: this.getPlayer().foodStock=this.getPlayer().foodStock+30;break;
		case BASEBALL_BAT: 
			Baseball_Bat b=new Baseball_Bat();
			this.weapon.add(b);break;
		case KATANA: 
			Katana k=new Katana();
			this.weapon.add(k);break;
		default: ;
		}
		this.getMap().grid[p.x][p.y].setDecor(Decor.GRASS);
	
	}
	
	
	
	/**
	 * La fonction steal permet à un character de tenter de voler dans la liste d'arme et dans le stock de nourriture d'un autre character ennemi
	 * Si il n'y a pas d'ennemi sur la case indiquée, il ne se passe rien
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void steal (char direction){
		
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if ((this.getMap().grid[p.x][p.y].getEntity_on() instanceof Survivor)&&(this.getMap().grid[p.x][p.y].getEntity_on().getPlayer()!=this.getPlayer())){
			Survivor ent_on=(Survivor)this.getMap().grid[p.x][p.y].getEntity_on();
			//vol d'armes de l'adversaire
			int alea=ent_on.weapon.size();
			int m=(int)(Math.random()*.3);
			if (alea<m){
				this.weapon.add(ent_on.weapon.get(m));
				ent_on.weapon.remove(m);	
			}
			//vol de nourriture de l'adversaire
			m=(int)(Math.random()*.5);
			if (ent_on.getPlayer().foodStock<m){
				this.getPlayer().foodStock=this.getPlayer().foodStock+ent_on.getPlayer().foodStock;
				ent_on.getPlayer().foodStock=0;
			}
			else{
				this.getPlayer().foodStock=this.getPlayer().foodStock+m;
				ent_on.getPlayer().foodStock=ent_on.getPlayer().foodStock-m;
			}
		}	
	}
	
	
	/**
	 * La fonction plant permet de faire apparaitre une pousse dans la case indiquée si le decor de cette case était de l'herbe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void plant (char direction){
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if (getMap().grid[p.x][p.y].getDecor()==Decor.GRASS){
			getMap().grid[p.x][p.y].setDecor(Decor.SPROUT) ;
		}		
	}
	
	/**
	 * La fonction water permet de faire d'une pousse un arbre 
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void water (char direction){
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if (getMap().grid[p.x][p.y].getDecor()==Decor.SPROUT){
			getMap().grid[p.x][p.y].setDecor(Decor.FOREST) ;
		}		
	}
	
	/**
	 * La fonction swap permet à deux joueurs de la même equipe de partager equitablement ou avec un leger avantage pour l'autre le nombre d'arme que l'on possède.
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void swap (char direction){
		
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if ((this.getMap().grid[p.x][p.y].getEntity_on() instanceof Survivor)&&(this.getMap().grid[p.x][p.y].getEntity_on().getPlayer()==this.getPlayer())){
			Survivor ent_on=(Survivor)this.getMap().grid[p.x][p.y].getEntity_on();
			//vol d'armes de l'adversaire
			int nb_arme_allie=ent_on.weapon.size();
			int nb_arme_moi=this.weapon.size();
			while (nb_arme_allie>nb_arme_moi+1){
				int m=(int)(Math.random()*nb_arme_allie);
				this.weapon.add(ent_on.weapon.get(m));
				ent_on.weapon.remove(m);
				nb_arme_allie=ent_on.weapon.size();
				nb_arme_moi=this.weapon.size();
			}
			while (nb_arme_moi>nb_arme_allie){
				int m=(int)(Math.random()*nb_arme_moi);
				ent_on.weapon.add(this.weapon.get(m));
				this.weapon.remove(m);
				nb_arme_allie=ent_on.weapon.size();
				nb_arme_moi=this.weapon.size();
			}
		}
		
	}
	
}
