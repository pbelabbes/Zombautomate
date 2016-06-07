package Model;

public enum Decor {

	ROCK(Action.PICK),//0
	GRASS(Action.MOVE),//1
	RABBIT(Action.WATER),//2
	APPLE(Action.ATTACK),//3
	SPROUT(Action.PLANT),//4
	FOREST(Action.DROP),//5
	BASEBALL_BAT(Action.HIDE),//6
	KATANA(Action.STEAL);//7

	private Action action;
	
	private Decor(Action action){
		this.action=action;
	}
	
	public Action getAction(){
		return this.action;
	}
	
}
