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
	
	public Player(int id ,String name, int foodStock){
		this.id = id ;
		this.name = name;
		//this.entities = entitie;
		this.foodStock = foodStock ;
		this.stone = 0;
		this.seed= 0 ;
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
	
	public void addFoodStock (int plus){
		this.foodStock = this.foodStock +plus;
		setChanged();
		notifyObservers(this.foodStock);
		return;
	}
	public int getFoodStock() {
		return foodStock;
	}
	/*public void setFoodStock(int foodStock) {
		this.foodStock = foodStock;
	}*/
	
	public void addStone(int plus){
		this.stone=this.stone+plus;
		setChanged();
		notifyObservers(this.stone);
		return;
	}
	public int getStone() {
		return stone;
	}
	/*public void setStone(int stone) {
		this.stone = stone;
	}
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
	/*public void setSeed(int seed) {
		this.seed = seed;
	}
	*/
	
	public void show() {
		System.out.println("id = "+Integer.toString( id));
		System.out.println("name =" + name );
		
		for (Character ch : entities){
			System.out.println("\tcharactère nb :" +entities.indexOf(ch));
			ch.showstat();
		}
		System.out.println("foodstock = "+Integer.toString( foodStock));
		System.out.println("stone = "+Integer.toString(stone));
		System.out.println("seed = "+Integer.toString(seed));
	}
	
}
