package Model;

public class Presence extends Condition2 {
	
	Character personnage;
	char direction;
	Player player;
	Zombie zombie;
	Decor decor;
	
	
	Presence(Character personnage,char direction,Player player){
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
