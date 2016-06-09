package Model;
import java.awt.Point;
import java.lang.String;

public class Presence extends Condition2 {
	
	Character personnage;
	char direction;
	String cible;
	Decor decor;
	
	Presence(Character personnage,char direction,String cible){
		 this.personnage=personnage;
		 this.direction=direction;
		 this.cible=cible;
		 decor = null;
		}
	Presence(Character personnage,char direction,Decor decor){
		 this.personnage=personnage;
		 this.direction=direction;
		 this.decor=decor;
		 cible = null;
		}
	
	public boolean execute(){
			Point p=new Point(this.personnage.getCell().getPosition());
			switch (direction){
			case 'N': p.y=p.y-1;break;
			case 'S': p.y=p.y+1;break;
			case 'E': p.x=p.x+1;break;
			case 'O': p.x=p.x-1;break;
			
			}	
			
	if(this.personnage.getMap().getGrid()[p.x][p.y].getEntity_on()!=null){

		if(decor != null)
		{
			return this.personnage.getMap().getGrid()[p.x][p.y].getDecor()==decor;	
		}
		else
		{
			switch (cible){
			
			case "allié":if(this.personnage.getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer()==this.personnage.getPlayer()) return true; 
						 else return false;
			case "zombie" :if(this.personnage.getMap().getGrid()[p.x][p.y].getEntity_on() instanceof Zombie)return true; 
						   else return false; 

			case "ennemi" : if(this.personnage.getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer()!=this.personnage.getPlayer()) return true; 
							else return false;
			default: return false;
			}
		}
	}
	else return false;
	
	}
			
			
			/*
			
public boolean execute(){
	Point p=new Point(this.personnage.getCell().getPosition());
	switch (direction){
	case 'N': p.y=p.y-1;break;
	case 'S': p.y=p.y+1;break;
	case 'E': p.x=p.x+1;break;
	case 'O': p.x=p.x-1;break;
					
	}	
	return this.personnage.getMap().getGrid()[p.x][p.y].getDecor()==decor;	}
				*/
}
