package Model;
import java.awt.Point;
import java.lang.String;

public class Presence extends Condition {
	

	private char direction;
	private String cible;
	private Decor decor;
	
	Presence(char direction,String cible){

		 this.direction=direction;
		 this.cible=cible;
		 decor = null;
		}
	Presence(char direction,Decor decor){

		 this.direction=direction;
		 this.decor=decor;
		 cible = null;
		}
	
	public boolean execute(Cell cellule){
//TODO On pourrait réduire un peu le code en utilisant getTargetedCell
			Point p=new Point(cellule.getPosition());
			int mapheight = cellule.getEntity_on().getMap().getHeight();
			int mapwidth = cellule.getEntity_on().getMap().getWidth();
			switch(direction)
			{
			case 'N' : p.y=(p.y-1+mapheight)%mapheight; break;
			case 'E' : p.x=(p.x+1+mapwidth)%mapwidth; break;
			case 'S' : p.y=(p.y+1+mapheight)%mapheight; break;
			default : p.x=(p.x-1+mapwidth)%mapwidth; break;
			}
			
	if(cellule.getEntity_on()!=null){


		if(this.decor != null)
		{

			System.out.println(cellule.getEntity_on().getMap().getGrid()[p.x][p.y].getDecor()!=null);
			return (cellule.getEntity_on().getMap().getGrid()[p.x][p.y].getDecor()==this.decor);	
		}
		else
		{
			switch (cible){
			
			case "allie":if(cellule.getEntity_on().getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()) return true; 
						 else return false;
			case "zombie" :if(cellule.getEntity_on().getMap().getGrid()[p.x][p.y].getEntity_on() instanceof Zombie)return true; 
						   else return false; 

			case "ennemi" : if(cellule.getEntity_on().getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()) return true; 
							else return false;
			default: return false;
			}
		}
	}
	else return false;
	
	}
			
		
}
