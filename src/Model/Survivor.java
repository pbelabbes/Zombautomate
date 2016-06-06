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
	ArrayList<Arme> arme;
		
	//constructeurs
	/**
	 * Constructeur d'un survivant:
	 * il part avec 100 points de vie
	 * sa force est de ??
	 * 
	 */
	public Survivor(Player player, Automata automata, Map map) {
		this.hp=100;
		this.strength=1;
		this.player=player;
		this.automata=automata;
		this.map=map;
		this.arme=null;
	}

	//Méthodes
	/**
	 * La fonction deplacer permet de déplacer le personnage dans une direction (Nord, Sud, Est, Ouest)
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action.
	 */
	public void deplacer (char direction) {
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
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
	 * La fonction deposer permet de poser une pierre sur de l'herbe
	 * Elle ne fait rien si le decor de la case indiquée ne correspond pas à de l'herbe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void deposer ( char direction){
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if (map.grid[p.x][p.y].decor==Decor.GRASS){
			map.grid[p.x][p.y].decor=Decor.ROCK;
		}				
	}
	
	
	/**
	 * La fonction ramasser permet de récupérer de la nourriture ou des armes posées sur le sol
	 * Elle met à jour les données du joueur et du personnage
	 * Elle ne fait aucune action si la case indiquée ne correspond pas au décor de nourriture ou d'arme
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void ramasser(char direction){
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		switch (this.map.grid[p.x][p.y-1].decor){
		case APPLE: this.player.foodStock=this.player.foodStock+ Nourriture.APPLE.getvalues();break;
		case RABBIT: this.player.foodStock=this.player.foodStock+30;break;
		case BASEBALL_BAT: 
			Baseball_Bat b=new Baseball_Bat();
			this.arme.add(b);break;
		case KATANA: 
			Katana k=new Katana();
			this.arme.add(k);break;
		default: ;
		}
		this.map.grid[p.x][p.y].decor=Decor.GRASS;
	
	}
	
	/**
	 * La fonction attaquer porte un coup vers la case indiquée
	 * Si un ennemi est present sur cette case, il perd des points de vie
	 * Si il y a un rock sur cette case elle se casse et on découvre soit un katana soit un lapin à sa place
	 * Sinon rien ne se passe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void attaquer(char direction){
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		
		if (map.grid[p.x][p.y].entity_on!=null){
			//On enlève des points de vie à l'adversaire
			map.grid[p.x][p.y].entity_on.hp--;
		}
		else {
			if (map.grid[p.x][p.y].decor==Decor.ROCK){
				int r= (int)(Math.random()*.2);
				if (r==0){
					map.grid[p.x][p.y].decor=Decor.RABBIT;
				}
				else{
					map.grid[p.x][p.y].decor=Decor.KATANA;
				}
			}
		}
	}
	
	/**
	 * La fonction voler permet à un character de tenter de voler dans la liste d'arme et dans le stock de nourriture d'un autre character ennemi
	 * Si il n'y a pas d'ennemi sur la case indiquée, il ne se passe rien
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void voler (char direction){
		
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if ((this.map.grid[p.x][p.y].entity_on instanceof Survivor)&&(this.map.grid[p.x][p.y].entity_on.player!=this.player)){
			Survivor ent_on=(Survivor)this.map.grid[p.x][p.y].entity_on;
			//vol d'armes de l'adversaire
			int alea=ent_on.arme.size();
			int m=(int)(Math.random()*.3);
			if (alea<m){
				this.arme.add(ent_on.arme.get(m));
				ent_on.arme.remove(m);	
			}
			//vol de nourriture de l'adversaire
			m=(int)(Math.random()*.5);
			if (ent_on.player.foodStock<m){
				this.player.foodStock=this.player.foodStock+ent_on.player.foodStock;
				ent_on.player.foodStock=0;
			}
			else{
				this.player.foodStock=this.player.foodStock+m;
				ent_on.player.foodStock=ent_on.player.foodStock-m;
			}
		}	
	}
	
	
	/**
	 * La fonction planter permet de faire apparaitre une pousse dans la case indiquée si le decor de cette case était de l'herbe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void planter (char direction){
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if (map.grid[p.x][p.y].decor==Decor.GRASS){
			map.grid[p.x][p.y].decor=Decor.SPROUT ;
		}		
	}
	
	/**
	 * La fonction arroser permet de faire d'une pousse un arbre 
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void arroser (char direction){
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if (map.grid[p.x][p.y].decor==Decor.SPROUT){
			map.grid[p.x][p.y].decor=Decor.FOREST ;
		}		
	}
	
	/**
	 * La fonction echanger permet à ddeux joueurs de la même equipe de partager equitablement ou avec un leger avantage pour l'autre le nombre d'arme que l'on possède.
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void echanger (char direction){
		
		Point p=new Point(this.cell.position);
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		if ((this.map.grid[p.x][p.y].entity_on instanceof Survivor)&&(this.map.grid[p.x][p.y].entity_on.player==this.player)){
			Survivor ent_on=(Survivor)this.map.grid[p.x][p.y].entity_on;
			//vol d'armes de l'adversaire
			int nb_arme_allie=ent_on.arme.size();
			int nb_arme_moi=this.arme.size();
			while (nb_arme_allie>nb_arme_moi+1){
				int m=(int)(Math.random()*nb_arme_allie);
				this.arme.add(ent_on.arme.get(m));
				ent_on.arme.remove(m);
				nb_arme_allie=ent_on.arme.size();
				nb_arme_moi=this.arme.size();
			}
			while (nb_arme_moi>nb_arme_allie){
				int m=(int)(Math.random()*nb_arme_moi);
				ent_on.arme.add(this.arme.get(m));
				this.arme.remove(m);
				nb_arme_allie=ent_on.arme.size();
				nb_arme_moi=this.arme.size();
			}
		}
		
	}
	
	/*
	 * les actions a implementer sont:
	 * 
	 * 	  déplacer   DONE
		  battre     DONE
		 Ramasser   DONE
		 Voler      DONE
		 echanger   DONE      
		 Planter    DONE 
		 Arroser    DONE 
		 Deposer    DONE
		 */  
	
}
