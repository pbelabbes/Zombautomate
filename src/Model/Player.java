/**
 * 
 */
package Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author pierrebelabbes
 *
 */
public class Player extends Observable{
	
	private int id;
	private String name;
	private ArrayList<Character> entities;
	private int foodStock;
	private int stone;
	private int seed;
	
	public Player(){
		
	}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param foodStock
	 */
	public Player(int id ,String name, int foodStock){
		this.id = id ;
		this.name = name;
		//this.entities = entitie;
		this.foodStock = foodStock ;
		this.stone = 10;
		this.seed= 10 ;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		setChanged();
		notifyObservers(this.id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers(this.name);
	}
	public ArrayList<Character> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Character> entities) {
		this.entities = entities;
		setChanged();
		notifyObservers(this.entities);
	}
	/**
	 * cette fonction permet de rajouter de la nourriture au stock de nourriture du joueur 
	 * @param plus
	 */
	public void addFoodStock (int plus){
		this.foodStock = this.foodStock +plus;
		setChanged();
		notifyObservers(this.foodStock);
		return;
	}
	public int getFoodStock() {
		return foodStock;
	}
	/**
	 * cette fonction permet de rajouter des cailloux au stock de cailloux su joueur 
	 * @param plus
	 */
	public void addStone(int plus){
		this.stone=this.stone+plus;
		setChanged();
		notifyObservers(this.stone);
		return;
	}
	public int getStone() {
		return stone;
	}
/**
 * 	cette fonction permet de rajouter des graines au stock de graine su joueur
 * @param plus
 */
	public void addSeed(int plus){
		this.seed=this.seed+plus;
		setChanged();
		notifyObservers(this.seed);
		return;
	}
	public int getSeed() {
		return seed;
	}
	
/**
 * cette fonction permet d'afficher un player 
 */
	public void show() {
		System.out.println("id = "+Integer.toString( id));
		System.out.println("name =" + name );
		
		for (Character ch : entities){
			System.out.println("\tcharactère nb :" +entities.indexOf(ch));
			ch.showstat();
			System.out.println("\n");
		}
		System.out.println("foodstock = "+Integer.toString( foodStock));
		System.out.println("stone = "+Integer.toString(stone));
		System.out.println("seed = "+Integer.toString(seed));
	}

	/**
	 * verifie si le joueur a perdu.
	 * @return true si aucune entité n'est encore en vie. faux sinon
	 */
	public boolean defeated()
	{
		return characters_remaining() == 0;
	}
	
	/**
	 * calclule le nombre de personnages encore en vie dans l'equipe du Player 
	 * @return nb de personnages vivants
	 */
	public int characters_remaining()
	{
		int compteur=0;
		for(Character c : this.entities)
		{
			if(c.is_alive()) compteur++;
		}
		return compteur;

	}
	
	/**
	 * Calcule le cout total des automates des personnages
	 * @return cout de l'équipe
	 */
	public int getCost()
	{
		int cout = 0;
		for(Character c : entities)
			cout += c.getAutomata().getCost();
		return cout;
	}
	
}
