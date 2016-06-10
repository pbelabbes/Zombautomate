/**
 * 
 */
package Model;

/**
 * @author zennouche
 *
 */
public class Et extends Condition2 {

	private Condition2 c1;
	private Condition2 c2;
	
	/**
	 * 
	 * @param c1  1ere condition
	 * @param c2  2eme condition
	 */
	public Et(Condition2 c1, Condition2 c2) 
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
