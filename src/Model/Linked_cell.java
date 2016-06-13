/**
 * 
 */
package Model;

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
	public boolean execute(Cell cellule) {

		Character owner = getTargetedCell(direction, cellule).getOwned_by();
		if(id_searched == 'N') return owner == null;

		else 
		{
			if(owner == null) return false;
			Player p1 = cellule.getEntity_on().getPlayer();
			Player p2 = owner.getPlayer();

			if (id_searched == 'A')
				return p1 == p2;

			else return p1 != p2;
		}
	}

}
