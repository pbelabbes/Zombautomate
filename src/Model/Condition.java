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
	 * @return
	 */
	protected Cell getTargetedCell(char direction, Cell cellule )
	{  	System.out.println(cellule.getPosition().toString());
		Point p = new Point(cellule.getPosition());
		switch(direction)
		{
		case 'N' : p.y=(p.y-1)%cellule.getEntity_on().getMap().getHeight(); break;
		case 'E' : p.x=(p.x+1)%cellule.getEntity_on().getMap().getWidth(); break;
		case 'S' : p.y=(p.y+1)%cellule.getEntity_on().getMap().getHeight(); break;
		default : p.x=(p.x-1)%cellule.getEntity_on().getMap().getWidth(); break;
		
		}
		return cellule.getEntity_on().getMap().getGrid()[p.x][p.y];
	}
}
