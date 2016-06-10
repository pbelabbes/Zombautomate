/**
 * 
 */
package Model;

/**
 * @author alexandre
 *
 */
public class Ou extends Condition {

	/* (non-Javadoc)
	 * @see Model.Condition#execute(Model.Cell)
	 */
	
	private Condition c1;
	private Condition c2;
	
	public Ou(Condition c1, Condition c2)
	{
		this.c1=c1;
		this.c2=c2;
	}
	
	@Override
	public boolean execute(Cell cellule) {

		return c1.execute(cellule) || c2.execute(cellule);
	}

}
