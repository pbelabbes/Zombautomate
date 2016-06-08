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
		boolean b;
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
					b=(per.getMap().getGrid()[p.x][p.y].getEntity_on().getPlayer() !=
					per.getCell().getEntity_on().getPlayer()) ;
				    return b;
				}
		}
		else { return false; }
			
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
	
	// fonction auxiliaire pour les suivantes
	
	private boolean in_intervalle(Point deb, Point fin, Point p){
		boolean a,b;
		a=((p.x>=deb.x)&&(p.x<=fin.x));
		b=((p.y>=deb.y)&&(p.y<=fin.y));
	   return (a && b);
		
	}
	public boolean CaseAlliee(char direction,Cell cell, Map map){
		Point p=new Point(cell.getPosition());
		boolean a,b;
		Cell ce;
		switch (direction){
		
		case 'N': p.y=p.y-1; break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		ce=map.getGrid()[p.x][p.y];
		a=(ce.getOwned_by() instanceof Survivor); 
		
		b=in_intervalle(ce.getOwned_by().getAutomata().getPosition(),ce.getOwned_by().getAutomata().proportion(),p);	
		
		return (b && a && (ce.getOwned_by().getPlayer()== cell.getOwned_by().getPlayer() ));
		
	}
	
	
	
	public boolean CaseEnnemi(char direction, Cell cell,Map map){
		Point p=new Point(cell.getPosition());
		boolean a,b;
		Cell ce;
		switch (direction){
		
		case 'N': p.y=p.y-1; break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		ce=map.getGrid()[p.x][p.y];
		a=(ce.getOwned_by() instanceof Survivor); 
		
		b=in_intervalle(ce.getOwned_by().getAutomata().getPosition(),ce.getOwned_by().getAutomata().proportion(),p);	
		
		return (a && b && (ce.getOwned_by().getPlayer()!= cell.getOwned_by().getPlayer() ));
		
	}

	//il suffit de savoir que la cellule sur laquelle on est n
	public boolean CaseNeutre(char direction, Cell cell, Map map){
		Point p=new Point(cell.getPosition());
		boolean a;
		Cell ce;
		switch (direction){
		
		case 'N': p.y=p.y-1; break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		ce=map.getGrid()[p.x][p.y];
		//on teste si l'automate de cette cellule n'appartient a aucun personnage ni zombie
		a=(ce.getOwned_by()==null);
		return (a);
		
	}
	
	public boolean ScanProche(){
		
	}
	

}
