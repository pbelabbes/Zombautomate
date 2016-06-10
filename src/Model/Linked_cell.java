/**
 * 
 */
package Model;

/**
 * @author alexandre
 *
 */
public class Linked_cell extends Condition2 {

	/* (non-Javadoc)
	 * @see Model.Condition2#execute()
	 */
	
	private char direction; //direction dans laquelle se fait l'action
	private char id_searched; //indique si on verifie case alliee, case ennemie ou case neutre
	
	
	/**
	 * 
	 * @param personnage le possesseur l'automate qui utilise cette condition
	 * @param direction 'N', 'E', 'S', 'O'
	 * @param id N : neutre, A : alliee ou E : ennemie
	 */
	public Linked_cell(Character personnage, char direction, char id)
	{
		this.personnage = personnage;
		this.direction = direction;
		this.id_searched = id;
	}
	@Override
	public boolean execute() {

		//TODO
		return true;
	}

}
