package Model;

//import java.awt.Point;

public class Zombie extends Character {

	public Zombie(Player player,Automata automata,Map map) 
	{
		super(player,automata,map);	

		strength = 34;
		sight_range = 15;
		hp = 30;

	}

	public Zombie(Zombie zombie)
	{
		super(zombie.getPlayer(),zombie.getAutomata(),zombie.getMap());
		strength = zombie.getStrength();
		sight_range = zombie.getSightRange();
		this.hp = 30;
		
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


	@Override
	public void eat()
	{
		//les zombies ne mangent pas
	}

		
	
}
