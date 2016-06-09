/**
 * 
 */
package Model;

import java.util.ArrayList;

/**
 * @author pierrebelabbes
 *
 */
public class Player {
	
	private int id;
	private String name;
	private ArrayList<Character> entities;
	private int foodStock;
	private int stone;
	private int seed;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Character> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Character> entities) {
		this.entities = entities;
	}
	
	public void addFoodStock (int plus){
		this.foodStock = this.foodStock +plus;
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
		return;
	}
	public int getSeed() {
		return seed;
	}
	/*public void setSeed(int seed) {
		this.seed = seed;
	}
	*/
	
}
