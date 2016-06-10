/**
 * 
 */
package Model;

import java.awt.Point;

/**
 * @author zennouche
 *
 */
public abstract class Condition2 {

	Character personnage;
	
	/**
	 * 
	 * @return boolean which verifies the condition
	 */
	public abstract boolean execute();
	
	public Cell getTargetedCell(char direction)
	{
		Point p = new Point(personnage.getCell().getPosition());
		switch(direction)
		{
		case 'N' : p.y%=(p.y-1); break;
		case 'E' : p.x%=(p.x+1); break;
		case 'S' : p.y%=(p.y+1); break;
		case 'O' : p.x%=(p.x-1);
		default :;
		
		}
		return personnage.getMap().getGrid()[p.x][p.y];
	}
}
