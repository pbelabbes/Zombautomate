package Model;

/**
 * @author pierrebelabbes
 *
 */

import java.util.ArrayList;
import java.awt.Point;


public class Automata {
//Atributs
		int etats;
		int inputs;
		
		caseAutomate[][] states;
        Point position; 
	
//constructeurs:
		Automata(int height, int width){
			this.etats=width;
			this.inputs=height;
			states=new caseAutomate[height][width];			
		}
		
		public void set_position(int x,int y){
			this.position.x=x;
			this.position.y=y;
		}
		
		public Point get_position(){
			return this.position;
		}
		//Methodes
		
		public void automate(ArrayList<etatAutomate> liste){
		
			etatAutomate etat;
			int i,j,k=0;
			i=0;j=0;
			while(k<liste.size()&& i<inputs){
					etat=liste.get(k); 
					if(j==etats){ i++; j=0;}
					else {j++;}
				  	states[i][etat.etat_courant]=etat.square;
			}
		}

		
		public void to_string(int width,int height){
		//car le nombre d'entrees est de 8 pour tous les automates
		for (int i=0;i<height;i++){
		     for (int j=0;j<width;j++){
		    	 System.out.println(this.states[i][j].etat_futur+",");
		     } 
		     System.out.println("\n");
		}
		
        }		
		public static void main(String[] args){
			Automata auto= new Automata(1,2);
			etatAutomate et_au=new etatAutomate();
			caseAutomate frame=
			ArrayList<etatAutomate> liste=new ArrayList<etatAutomate>();
			liste={{}
			auto.automate(liste);
			System.out.println("nous allons afficher le tableau des actions \n");
			auto.to_string (1,2);
		}
}

		

		
		




