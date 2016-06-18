/**
 * 
 */
package model.conditions;
import model.jeu.*;

/**
 * @author alexandre
 *
 */
public class Linked_cell extends Condition {

	/* (non-Javadoc)
	 * @see Model.Condition2#execute()
	 */
	
	private char direction; //direction dans laquelle se fait l'action
	private char id_searched; //indique si on verifie case alliee, case ennemie ou case neutre
	
	
	/**
	 * 
	 * @param personnage le possesseur l'automate qui utilise cette condition
	 * @param direction 'N', 'E', 'S', 'O'
	 * @param id 'N' : neutre, 'A' : alliee ou 'E' : ennemie
	 */
	public Linked_cell(char direction, char id)
	{
		this.direction = direction;
		this.id_searched = id;
	}

	@Override
	/**
	 * cette fonction permet de verifier si a la cellule a la direction donnée appartient  à un automate 
	 * d'un allié ou d'un ennemi
	 * @parma:Cell cellule: on passe la cellule du personnage en parametre 
	 * @return boolean
	 */
	public boolean execute(Cell cellule) {

        
        Cell ce=getTargetedCell(direction, cellule);

        Character c = ce.getOwned_by();
		if(c==null) 
		{
				return id_searched == 'N';
		}
		else 
		{ 
			Player p1 = cellule.getEntity_on().getPlayer();
			Player p2 = c.getPlayer();

			if (id_searched == 'A')
				return p1 == p2;

			else return p1 != p2;
		}
	}
}

