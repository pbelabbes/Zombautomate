package Model;

/**
 * Ce type enum liste tous les décors présents sur le terrain associés à l'action qu'ils représentent  
 * 
 *
 */
public enum Decor {

	ROCK(Action.PICK),//0
	GRASS(Action.MOVE),//1
	RABBIT(Action.WATER),//2
	APPLE(Action.ATTACK),//3
	SPROUT(Action.PLANT),//4
	TREE(Action.DROP),//5
	BASEBALL_BAT(Action.SWAP),//6
	KATANA(Action.STEAL);//7

	private Action action;
	
	private Decor(Action action){
		this.action=action;
	}
	
	public Action getAction(){
		return this.action;
	}
	
	public static Decor getDecor(Action action)
	{
		switch(action)
		{
		case PICK : return ROCK;
		case MOVE : return GRASS;
		case WATER : return RABBIT;
		case ATTACK : return APPLE;
		case PLANT : return SPROUT;
		case DROP : return TREE;
		case SWAP : return BASEBALL_BAT;
		default: return KATANA;
		}
	}
	
}
