package Model;

import java.awt.Point;

public class Condition_fonction {
	
	
	
	
	//Methodes4
	//arg: une direction et une cible 
	public boolean Present(char direction , Character per , Point position){
	
		Point p=new Point(position);
		switch (direction){
		
		case 'N': p.y=p.y-1;break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;		
		}
		if(map.grid[p.x][p.y].getEntity_on()==Survivor){
			return (map.grid[p.x][p.y].getEntity_on().player !=
			getMap().grid[position.x][position.y].getEntity_on().player) ;
		}			
	
	}
	public boolean present(char direction , Decor d,Point position){
		Point p=new Point(position);
		switch (direction){
		
		case 'N': p.y=p.y-1;break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		return map.grid[p.x][p.y].getDecor()==d;
					
	}
	
	public boolean Scanloin(){
		
	}
	
	public boolean Et(){
		
	}
    
	public boolean CaseAlliee(){
		
	}
	
	public boolean CaseEnnemi(){
		
	}
	
	public boolean CaseNeutre(){
		
	}
	
	public boolean ScanProche(){
		
	}
	

}
