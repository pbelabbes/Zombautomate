package Model;
import java.awt.Point;

//import jus.util.assertion.Require;

public class Survivor extends Character{

	public Survivor() {
	// TODO Auto-generated constructor stub
	}
	//Attributs
	ArrayList arme;
	
	//constructeurs
	
	//creer un nouveau personnage
	//public survivor();
	
	public void deplacer (char direction) {
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
		switch (direction){
		//on modifie la position et donc la cellule du personnage
		
		case 'O': this.cell=map.grid[x+1][y] ;break;
		case 'S': this.cell=map.grid[x][y+1] ;break;
		case 'N': this.cell=map.grid[x][y-1] ;break;
		case 'E': this.cell=map.grid[x-1][y] ;break; 
		default : //throw new Require ("visible"); 
			     break;
		}	
	}
	
	
	/*
	 * tout ce qu'on peut deposer est un rocher 
	 * 
	 */
	public void deposer ( char direction){
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
		
		switch (direction){
		case 'O':map.grid[x+1][y].decor=Decor.ROCK;break;
			
		case 'S':map.grid[x][y+1].decor=Decor.ROCK;break;
			
		case 'N':map.grid[x][y-1].decor=Decor.ROCK;break;
			
		case 'E':map.grid[x-1][y].decor=Decor.ROCK; break;
			
		default :break;
		
		}				
	}
	
	
	public void battre (char direction){
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
		
		switch (direction){
		case 'O': map.grid[x+1][y].entity_on.hp--;break;
			
		case 'S': map.grid[x][y+1].entity_on.hp--;break;
			
		case 'N': map.grid[x][y-1].entity_on.hp--;break;
			
		case 'E':  map.grid[x-1][y].entity_on.hp--;break;
			
		default : break;
		
		}					
		
	}
	
	
	public void voler (char direction,Decor decor){
		
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
		int alea=this.cell.armes.size();
		
		switch (direction){
		case 'O': switch (decor) {
				  case RABBIT: case APPLE :
					//on vole toute la nourriture de l'ennemi 
					this.cell.entity_on.player.foodStock=this.cell.entity_on.player.foodStock+
					map.grid[x+1][y].entity_on.player.foodStock;
					map.grid[x+1][y].entity_on.player.foodStock=0;
					break;
					//dans ce cas on vole une arme au hasard
				  case KATANA: case BASEBALL_BAT :
						map.grid[x+1][y].entity_on.player.--;
						this.cell.entity_on.player.foodStock++;
						break;
					}
						
		case 'S':  switch (decor) {
					 case RABBIT: case APPLE :
							//on vole toute la nourriture de l'ennemi 
							this.cell.entity_on.player.foodStock=this.cell.entity_on.player.foodStock+
							map.grid[x][y+1].entity_on.player.foodStock;
							map.grid[x][y+1].entity_on.player.foodStock=0;
							break;
							}
				
		case 'N':  switch (decor) {
		 			case RABBIT: case APPLE :
					//on vole toute la nourriture de l'ennemi 
					this.cell.entity_on.player.foodStock=this.cell.entity_on.player.foodStock+
					map.grid[x][y-1].entity_on.player.foodStock;
					map.grid[x][y-1].entity_on.player.foodStock=0;
					break;
					}	
		case 'E':   switch (decor) {
					 case RABBIT: case APPLE :
							//on vole toute la nourriture de l'ennemi 
							this.cell.entity_on.player.foodStock=this.cell.entity_on.player.foodStock+
							map.grid[x+1][y].entity_on.player.foodStock;
							map.grid[x+1][y].entity_on.player.foodStock=0;
							break;
						}
		default : break;
		
		}					
				
	}
	
	public void planter (char direction){
		
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
		switch (direction){
		//on modifie la position et donc la cellule du personnage
		
		case 'O': map.grid[x+1][y].decor=Decor.SPROUT ;break;
		case 'S': map.grid[x][y+1].decor=Decor.SPROUT ;break;
		case 'N': map.grid[x][y-1].decor=Decor.SPROUT ;break;
		case 'E': map.grid[x-1][y].decor=Decor.SPROUT ;break; 
		default :
			     break;
		}		
	}
	
	
	public void arroser (char direction){
		
		int x,y;
		x=this.cell.position().x;
		y=this.cell.position().y;
		switch (direction){
		//on modifie la position et donc la cellule du personnage
		
		case 'O': map.grid[x+1][y].decor=Decor.FOREST ;break;
		case 'S': map.grid[x][y+1].decor=Decor.FOREST ;break;
		case 'N': map.grid[x][y-1].decor=Decor.FOREST ;break;
		case 'E': map.grid[x-1][y].decor=Decor.FOREST ;break; 
		default :
			     break;
		}		
	}
	
	public void echange (char direction, Decor decor){
		
		
		
	}
	
	/*
	 * les actions a implementer sont:
	 * 
	 * 	  déplacer   DONE
	 
		  battre     DONE
		 Ramasser   ALICE 
		 Voler      ENTRAIN (HALF DONE STILL WEAPON'S CASE TO DO)      
		 Planter    DONE 
		 Arroser    DONE 
		 Deposer    DONE
		 */  
	
}
