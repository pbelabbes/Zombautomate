package Model;

//import java.awt.Point;

public class Zombie extends Character {

	public Zombie(Player player,Automata automata,Map map) 
	{
		super(player,automata,map);	

		strength = 3;
		sight_range = 5;

	}

	
	@Override
	public void act(Action action, char direction) 
	{
		Zombie debug = this;
		Cell cellule = getTargetedCell(direction,this.cell);
		switch(action)
		{
		case ATTACK : attaquer(cellule);
		case MOVE : deplacer(cellule);
		default:;
		}
	}


	@Override
	public void eat()
	{
		//les zombies ne mangent pas
	}

		
	
}
