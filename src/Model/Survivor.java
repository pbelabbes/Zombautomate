package Model;

import java.awt.Point;
import java.util.ArrayList;

public class Survivor extends Character{

	ArrayList<Arme> arme;
	
	
	public Survivor() {
		// TODO Auto-generated constructor stub
		
		
		
		
		public ramasser(char direction){
			Point p=new Point(this.cell.position);
			switch (direction){
			case 'N': p.y=p.y-1;break;				
			case 'S': p.y=p.y+1;break;
			case 'E': p.x=p.x+1;break;
			case 'O': p.x=p.x-1;break;
			default: ;
			}
			switch (this.map.grid[p.x][p.y-1].decor){
			case APPLE: this.player.foodStock=this.player.foodStock+ Nourriture.APPLE.getvalues();break;
			case RABBIT: this.player.foodStock=this.player.foodStock+30;break;
			case BASEBALL_BAT: 
				Baseball_Bat b=new Baseball_Bat();
				this.arme.add(b);break;
			case KATANA: 
				Katana k=new Katana();
				this.arme.add(k);break;
			default: ;
			}
			this.map.grid[p.x][p.y].decor=Decor.GRASS;
		
		}
	}

}
