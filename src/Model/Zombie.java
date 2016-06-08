package Model;

import java.awt.Point;

public class Zombie extends Character {

	public Zombie(Player player,Automata automata,Map map) {
		super(player,automata,map);
		
	}

	public void attaquer(char direction){
		Point p=new Point(this.getCell().getPosition());
		switch (direction){
		case 'N': p.y=p.y-1;break;				
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		default: ;
		}
		
		if (getMap().grid[p.x][p.y].getEntity_on()!=null  && getMap().getGrid()[p.x][p.y].getDecor()!=Decor.TREE){
			//On enlève des points de vie à l'adversaire
			getMap().getGrid()[p.x][p.y].getEntity_on().hp--;
		}
		else {
			if (getMap().getGrid()[p.x][p.y].getDecor()==Decor.ROCK){
				int r= (int)(Math.random()*.2);
				if (r==0){
					getMap().getGrid()[p.x][p.y].setDecor(Decor.RABBIT);
				}
				else{
					getMap().getGrid()[p.x][p.y].setDecor(Decor.KATANA);
				}
			}
		}
	}
}
