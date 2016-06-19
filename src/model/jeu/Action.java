/**
 * 
 */
package model.jeu;
import model.decors.*;
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
	
	public static Action getAction(Decor decor)
	{
		if(decor==null)
		{
			System.out.println("le decor converti est nul");
			return null;
		}
		else
			switch(decor)
			{
			case ROCK: return Action.PICK;
			case GRASS: return Action.MOVE;
			case RABBIT: return Action.WATER;
			case APPLE: return Action.ATTACK;
			case SPROUT: return Action.PLANT;
			case TREE: return Action.DROP;
			case BASEBALL_BAT: return Action.SWAP;
			case KATANA:
			default: return Action.STEAL;
			}
	}
	
}


