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
		if (getMap().getGrid()[p.x][p.y].getDecor()==Decor.GRASS){
			getMap().getGrid()[p.x][p.y].setDecor(Decor.ROCK);
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
			getMap().getGrid()[p.x][p.y].getEntity_on().setHp(getMap().getGrid()[p.x][p.y].getEntity_on().getHp()-1);
		}
		else {
			if (getMap().getGrid()[p.x][p.y].getDecor()==Decor.ROCK){
				int r= (int)(Math.random()*.2);
				if (r==0){
					getMap().getGrid()[p.x][p.y].setDecor(Decor.RABBIT);
				}
				else{
					getMap().getGrid()[p.x][p.y].setDecor(Decor.KATANA);
				}
			}
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
		switch (this.getMap().getGrid()[p.x][p.y].getDecor()){
		case APPLE: this.getPlayer().addFoodstock(Nourriture.APPLE.getvalues());break;
		case RABBIT: this.getPlayer().addFoodstock(Nourriture.RABBIT.getvalues());break;
		case BASEBALL_BAT: 
			Baseball_Bat b=new Baseball_Bat();
			this.weapon.add(b);break;
		case KATANA: 
			Katana k=new Katana();
			this.weapon.add(k);break;
		default: ;
		}
		this.getMap().getGrid()[p.x][p.y].setDecor(Decor.GRASS);
	
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
		//On vérifie qu'il y a bien un survivant sur la case désignée et qu'il n'est pas de notre équipe
		if ((this.getMap().getGrid()[p.x][p.y].getEntity_on() instanceof Survivor)&&(this.getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer()!=this.getPlayer())){
			Survivor ent_on=(Survivor)this.getMap().getGrid()[p.x][p.y].getEntity_on();
			//vol d'armes de l'adversaire
			int alea=ent_on.weapon.size();
			int m=(int)(Math.random()*.3);
			if (alea<m){
				this.weapon.add(ent_on.weapon.get(m));
				ent_on.weapon.remove(m);	
			}
			//vol de graines
			m=(int)(Math.random()*.5);
			int pl=ent_on.getPlayer().getSeed();
			if (pl<m){
				this.getPlayer().addSeed(pl);
				ent_on.getPlayer().addSeed(-pl);
			}
			else{
				this.getPlayer().addSeed(m);
				ent_on.getPlayer().addSeed(-m);
			}
			//vol de cailloux
			m=(int)(Math.random()*.5);
			pl=ent_on.getPlayer().getStone();
			if (pl<m){
				this.getPlayer().addStone(pl);
				ent_on.getPlayer().addStone(-pl);
			}
			else{
				this.getPlayer().addStone(m);
				ent_on.getPlayer().addStone(-m);
			}
			//vol de nourriture de l'adversaire
			m=(int)(Math.random()*.5);
			pl=ent_on.getPlayer().getFoodStock();
			if (pl<m){
				this.getPlayer().addFoodstock(pl);
				ent_on.getPlayer().addFoodstock(-pl);
			}
			else{
				this.getPlayer().addFoodstock(m);
				ent_on.getPlayer().addFoodstock(-m);
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
		if (getMap().getGrid()[p.x][p.y].getDecor()==Decor.GRASS){
			getMap().getGrid()[p.x][p.y].setDecor(Decor.SPROUT) ;
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
		if (getMap().getGrid()[p.x][p.y].getDecor()==Decor.SPROUT){
			getMap().getGrid()[p.x][p.y].setDecor(Decor.TREE) ;
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
		if ((this.getMap().getGrid()[p.x][p.y].getEntity_on() instanceof Survivor)&&(this.getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer()==this.getPlayer())){
			Survivor ent_on=(Survivor)this.getMap().getGrid()[p.x][p.y].getEntity_on();
			//vol d'armes de l'adversaire
			int nb_weapon_ally=ent_on.weapon.size();
			int nb_weapon_me=this.weapon.size();
			while (nb_weapon_ally>nb_weapon_me+1){
				int m=(int)(Math.random()*nb_weapon_ally);
				this.weapon.add(ent_on.weapon.get(m));
				ent_on.weapon.remove(m);
				nb_weapon_ally=ent_on.weapon.size();
				nb_weapon_me=this.weapon.size();
			}
			while (nb_weapon_me>nb_weapon_ally){
				int m=(int)(Math.random()*nb_weapon_me);
				ent_on.weapon.add(this.weapon.get(m));
				this.weapon.remove(m);
				nb_weapon_ally=ent_on.weapon.size();
				nb_weapon_me=this.weapon.size();
			}
		}
		
	}

	
}
