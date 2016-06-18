/**
 * 
 */
package model.conditions;
import model.jeu.*;
/**
 * @author zennouche
 *
 */
public class Et extends Condition {

	private Condition c1;
	private Condition c2;
	/**
	 * 
	 * @param c1  1ere condition
	 * @param c2  2eme condition
	 */

	public Et(Condition c1, Condition c2) 
	{
		this.c1=c1;
		this.c2=c2;
	}

	@Override
	public boolean execute(Cell cellule)
	{
		return c1.execute(cellule) && c2.execute(cellule);

	}


}
