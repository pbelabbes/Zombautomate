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
	
	//Getter et Setter
	public ArrayList<Arme> getWeapon() {
		return weapon;
	}
	public void setWeapon(ArrayList<Arme> weapon) {
		this.weapon = weapon;
	}

	//Constructeurs
	/**
	 * Constructeur d'un survivant:
	 * il part avec 100 points de vie
	 * sa force est de ??
	 * 
	 */
	public Survivor(Player player, Automata automata, Map map) {
		super(player, automata, map);
		this.weapon=new ArrayList<Arme>();
	}

	//Méthodes

	
	/**
	 * La fonction eat permet à chaque tour de faire manger les survivants grace au stock de nourriture
	 * Si il n'y a plus de nourriture en stock le survivant perd un point de vie.
	 */
	public void eat()
	{
		if (this.getPlayer().getFoodStock()>0){
			this.getPlayer().addFoodStock(-1);
		}
		else {
			this.supHp(1);
		}
	}
	/**
	 * La fonction drop permet de poser une pierre sur de l'herbe
	 * Elle ne fait rien si le decor de la case indiquée ne correspond pas à de l'herbe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void drop (Cell cellule){

		if (cellule.getDecor()==Decor.GRASS && this.getPlayer().getStone()>10){
			cellule.setDecor(Decor.ROCK);
			this.getPlayer().addStone(-10);
		}				
	}
	
	
	/**
	 * La fonction pick permet de récupérer de la nourriture ou des armes posées sur le sol
	 * Elle met à jour les données du joueur et du personnage
	 * Elle ne fait aucune action si la case indiquée ne correspond pas au décor de nourriture ou d'arme
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void pick(Cell cellule)
	{
		switch (cellule.getDecor()){
		case APPLE: this.getPlayer().addFoodStock(Nourriture.APPLE.getvalues());break;
		case RABBIT: this.getPlayer().addFoodStock(Nourriture.RABBIT.getvalues());break;
		case BASEBALL_BAT: 
			Baseball_Bat b=new Baseball_Bat();
			this.weapon.add(b);break;
		case KATANA: 
			Katana k=new Katana();
			this.weapon.add(k);break;
		default: ;
		}
		cellule.setDecor(Decor.GRASS);
	}
	
	/**
	 * La fonction steal permet à un character de tenter de voler dans la liste d'arme et dans le stock de nourriture d'un autre character ennemi
	 * Si il n'y a pas d'ennemi sur la case indiquée, il ne se passe rien
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void steal (Cell cellule){
		
		Survivor ent_on = (Survivor) cellule.getEntity_on();
		//On vérifie qu'il y a bien un survivant sur la case désignée et qu'il n'est pas de notre équipe
		if (ent_on != null && ent_on.getPlayer() != this.player && !(cellule.getEntity_on() instanceof Zombie)){

			//vol d'armes de l'adversaire
			int alea=ent_on.getWeapon().size();
			int m=(int)(Math.random()*.3);
			if (alea<m){
				this.weapon.add(ent_on.getWeapon().get(m));
				ent_on.getWeapon().remove(m);	
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
				this.getPlayer().addFoodStock(pl);
				ent_on.getPlayer().addFoodStock(-pl);
			}
			else{
				this.getPlayer().addFoodStock(m);
				ent_on.getPlayer().addFoodStock(-m);
			}
		}	
	}
		
	/**
	 * La fonction plant permet de faire apparaitre une pousse dans la case indiquée si le decor de cette case était de l'herbe
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void plant (Cell cellule){
		
		if (cellule.getDecor()==Decor.GRASS){
			cellule.setDecor(Decor.SPROUT) ;
		}		
	}
	
	/**
	 * La fonction water permet de faire d'une pousse un arbre 
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void water (Cell cellule){
		
		if (cellule.getDecor()==Decor.SPROUT){
			cellule.setDecor(Decor.TREE) ;
		}		
	}
	
	/**
	 * La fonction swap permet à deux joueurs de la même equipe de partager equitablement ou avec un leger avantage pour l'autre le nombre d'arme que l'on possède.
	 * @param direction: indique la case adjacente dans laquelle effectuer l'action
	 */
	public void swap (Cell cellule){

		Survivor target = (Survivor) cellule.getEntity_on();
		if(target == null || target.getPlayer() != this.getPlayer())
			return;
		else {
			int nb_weapon_ally=target.getWeapon().size();
			int nb_weapon_me=this.weapon.size();
			while (nb_weapon_ally>nb_weapon_me+1){
				int m=(int)(Math.random()*nb_weapon_ally);
				this.weapon.add(target.getWeapon().get(m));
				target.getWeapon().remove(m);
				nb_weapon_ally=target.getWeapon().size();
				nb_weapon_me=this.weapon.size();
			}
			while (nb_weapon_me>nb_weapon_ally){
				int m=(int)(Math.random()*nb_weapon_me);
				target.getWeapon().add(this.weapon.get(m));
				this.weapon.remove(m);
				nb_weapon_ally=target.getWeapon().size();
				nb_weapon_me=this.weapon.size();
			}
		}
		
	}
	
	@Override
	public void act(Action action, char direction)
	{
		System.out.println(action);
		Cell cellule = this.getTargetedCell(direction, this.getCell());
		switch(action)
		{
		case WATER: water(cellule); break;
		case SWAP: swap(cellule); break;
		case STEAL: steal(cellule); break;
		case PLANT: plant(cellule); break;
		case PICK: pick(cellule); break;
		case ATTACK : attaquer(cellule); break;
		case MOVE: deplacer(cellule); break;
		case DROP: drop(cellule);
		default :;
		}
	}
	
	
}
