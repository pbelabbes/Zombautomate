/**
 * 
 */
package Model;

/**
 * Voici la liste des actions possibles par un personnage
 *
 */
public enum Action {
	
	
	PICK(Decor.ROCK),//0
	MOVE(Decor.GRASS),//1
	WATER(Decor.RABBIT),//2
	ATTACK(Decor.APPLE),//3
	PLANT(Decor.SPROUT),//4
	DROP(Decor.TREE),//5
	SWAP(Decor.BASEBALL_BAT),//6
	STEAL(Decor.KATANA);//7

	
	
	private Decor decor;
	
	private Action(Decor decor){
		this.decor=decor;
	}
	
	public Decor getDecor(){
		return this.decor;
	}
	
}


