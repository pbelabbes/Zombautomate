package Model;

import java.awt.Point;

public class Condition_fonction {
	
	
	
	
	//Methodes4
	//arg: une direction et une cible 
	/*
	 * on donne la position
	 */
	public boolean Present(char direction , Character per , Point position){
	
		Point p=new Point(position);
		switch (direction){
		
		case 'N': p.y=p.y-1;break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;		
		}
		if(per.getMap().getGrid()[p.x][p.y].getEntity_on() instanceof Character){
			if(per.getMap().getGrid()[p.x][p.y].getEntity_on() instanceof Zombie){
					return false;
				}
				else {
					//si ils appartiennent pas au meme joueuer alors ils sont ennemies et pas alliés
					return (per.getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer() !=
					per.getMap().getGrid()[position.x][position.y].getEntity_on().getPlayer()) ;
				}
		}
			
	}
	
	
//la fonction renvoie le type de decor qu'il y'a a la case a cote qui est dans la direction "direction"
	
	public boolean present(char direction , Decor d , Point position, Map map){
		Point p=new Point(position);
		switch (direction){
		
		case 'N': p.y=p.y-1;break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		return map.getGrid()[p.x][p.y].getDecor()==d;
					
	}
	
	public boolean Scanloin(){
	
		
	}
	
	public boolean Et(){
		
	}
    
	public bollean Ou(){
		
	}
	
	//
	public boolean CaseAlliee(char direction,Character per){
	Point p=new Point(per.)
		
		
		
	}
	
	public boolean CaseEnnemi(char direction, Character per){
		
	}
	
	public boolean CaseNeutre(char direction, Character per){
		
		
	}
	
	public boolean ScanProche(){
		
	}
	

}
