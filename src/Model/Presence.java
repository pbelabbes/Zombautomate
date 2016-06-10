package Model;

public class Presence extends Condition2 {
	
	private Character personnage;
	private char direction;
	private Player player;
	private Zombie zombie;
	private Decor decor;
	
	
	public Presence(Character personnage,char direction,Player player){
      this.personnage=personnage;
      this.player=player;
      this.direction=direction;
	}
	
	Presence(Character personnage,char direction,Zombie zombie){
	      this.personnage=personnage;
	      this.zombie=zombie;
	      this.direction=direction;
		}
	
	Presence(Character personnage,char direction,Decor decor){
	      this.personnage=personnage;
	      this.decor=decor;
	      this.direction=direction;
		}

	
	
	
}
