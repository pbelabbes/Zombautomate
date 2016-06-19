/**
 * 
 */
package model.conditions;
import model.jeu.*;
/**
 * @author alexandre
 *
 */
public class Defaut extends Condition {

	/* (non-Javadoc)
	 * @see Model.Condition#execute(Model.Cell)
	 */

	public Defaut()
	{
	//Defaut renvoie toujours vrai quoi qu'il arrive. Il n'est donc pas nécessaire d'y initialiser une valeur
	}
	
	@Override
	public boolean execute(Cell cellule) {
		return true;
	}

}
