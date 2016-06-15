package Model;
//import java.awt.Point;
import java.lang.String;

public class Presence extends Condition {


	private char direction;
	private String cible;
	private Decor decor;

	/**
	 * 
	 * @param direction
	 * @param cible
	 */
	Presence(char direction,String cible){

		this.direction=direction;
		this.cible=cible;
		decor = null;
	}
	/**
	 * 
	 * @param direction
	 * @param decor
	 */
	Presence(char direction,Decor decor){

		this.direction=direction;
		this.decor=decor;
		cible = null;
	}

 /**
  * cette fonction permet de verifier si à la direction donnée on trouve le decor ou 
  * la cible recherchée
  * Cell cellule : on donne en parametre la cellule du  personnage 
  */
	public boolean execute(Cell cellule){

		Cell targeted_cell = getTargetedCell(this.direction,cellule);
		
		if(this.decor != null)
		{

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
