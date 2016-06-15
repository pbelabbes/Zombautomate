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

		/*			int mapheight = cellule.getEntity_on().getMap().getHeight();
			int mapwidth = cellule.getEntity_on().getMap().getWidth();
			switch(direction)
			{
			case 'N' : p.y=(p.y-1+mapheight)%mapheight; break;
			case 'E' : p.x=(p.x+1+mapwidth)%mapwidth; break;
			case 'S' : p.y=(p.y+1+mapheight)%mapheight; break;
			default : p.x=(p.x-1+mapwidth)%mapwidth; break;
			}
		 */			

		Cell targeted_cell = getTargetedCell(this.direction,cellule);
		
		if(this.decor != null)
		{

//			System.out.println(cellule.getEntity_on().getMap().getGrid()[p.x][p.y].getDecor()!=null);
			return (targeted_cell.getDecor()==this.decor);	
		}
		else
		{
			Character c1 = cellule.getEntity_on();
			Character c2 = targeted_cell.getEntity_on();
			
			if(c2 != null)
			{
				switch (cible)
				{
				case "allie":if(c1.getPlayer()==c2.getPlayer()) return true; 
				else return false;
				case "zombie" :if(c2 instanceof Zombie)return true; 
				else return false; 
	
				case "ennemi" : 
					if(c1 instanceof Zombie)
						return (c1.getPlayer()!=c2.getPlayer());
					else return (c1.getPlayer()!=c2.getPlayer() && c2 instanceof Survivor);
				
				default: return false;
				}
		
			} else return false;
		}

		

	}


}
