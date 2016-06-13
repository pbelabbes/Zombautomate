/**
 * 
 */
package Model;

import java.awt.Point;

/**
 * @author zennouche
 *
 */
public abstract class Condition {

	
	/**
	 * 
	 * @return boolean which verifies the condition
	 */

	public abstract boolean execute(Cell cellule);

	/**
	 * cette fonction permet de recuperer la cellule 
	 * qui est a une direction precise de la cellule donnée en paramtere
	 * 
	 * @param direction
	 * @param cellule
	 * @return Cellule ciblée
	 */
	protected Cell getTargetedCell(char direction, Cell cellule )
	{
		Point p = new Point(cellule.getPosition());
		switch(direction)
		{
		case 'N' : p.y%=(p.y-1); break;
		case 'E' : p.x%=(p.x+1); break;
		case 'S' : p.y%=(p.y+1); break;
		case 'O' : p.x%=(p.x-1);
		default :;
		
		}
		return cellule.getEntity_on().getMap().getGrid()[p.x][p.y];
	}
}
