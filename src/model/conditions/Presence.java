package model.conditions;
import model.decors.*;

import model.jeu.*;

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
	public Presence(char direction,String cible){

		this.direction=direction;
		this.cible=cible;
		decor = null;
	}

	/**
	 * 
	 * @param direction
	 * @param decor
	 */
	
	public Presence(char direction,Decor decor){

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
			model.jeu.Character c1 = cellule.getEntity_on();
			model.jeu.Character c2 = targeted_cell.getEntity_on();
			
			if(c2 != null)
			{
				switch (cible)
				{
				case "allie":if(c1.getPlayer()==c2.getPlayer()) return true; 
				else return false;
				case "zombie" :if(c2 instanceof Zombie)return true; 
				else return false; 

				//meme les ennemis des zombies sont des survivors 
				case "ennemi" : return (c1.getPlayer()!=c2.getPlayer() && c2 instanceof Survivor);
				/*	if(c1 instanceof Zombie)
=======
	//attention, il faut nettoyer
				case "ennemi" : 
					if(c1 instanceof Zombie)
>>>>>>> 03d7cfac132b2ac4512b5565cf91aec239a740c9
						if((c1.getPlayer()!=c2.getPlayer()))
							return (c1.getPlayer()!=c2.getPlayer());
						else return false;
					else return (c1.getPlayer()!=c2.getPlayer() && c2 instanceof Survivor);
				*/
				default: return false;
				}
		
			} else return false;
		}
}
	
}
