package Model;

public enum Decor {

	ROCK(Action.RAMASSER),//0
	GRASS(Action.DEPLACER),//1
	RABBIT(Action.ARROSER),//2
	APPLE(Action.ATTAQUER),//3
	SPROUT(Action.PLANTER),//4
	FOREST(Action.DEPOSER),//5
	BASEBALL_BAT(Action.ECHANGER),//6
	KATANA(Action.VOLER);//7

	private Action action;
	
	private Decor(Action action){
		this.action=action;
	}
	
	public Action getAction(){
		return this.action;
	}
}
