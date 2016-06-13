package Model;

import java.awt.Point;

public class Zombie extends Character {

	public Zombie(Player player,Automata automata,Map map) 
	{
		super(player,automata,map);	
	}

	@Override
	public void act(Action action, char direction) 
	{
		Cell cellule = getTargetedCell(direction,this.cell);
		switch(action)
		{
		case ATTACK : attaquer(cellule);
		case MOVE : deplacer(cellule);
		default:;
		}
	}

		
	
}
