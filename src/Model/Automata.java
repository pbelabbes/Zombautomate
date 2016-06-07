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
		
		private void ajoute(etatAutomate etat){
			
		if(inputs==0 ||etats==0) return;
		int i=0;
		int j=0;
		while(i<etats && j<inputs && !etat.square.equals(states[i][j])){
			
					if(etat.square.equals(states[i][j])){
					   states[i][etat.etat_courant]=etat.square;	
			  		}
					else if(j==inputs){j=0;i++;}
					else j++;
		}
		
	}	
		public void automate(ArrayList<etatAutomate> liste){
		
			etatAutomate etat;
			for(int i=0;i<liste.size();i++){
				etat=liste.get(i);
				ajoute(etat);
			}
		}

	
		
		/*
		 * La fonction qui permet d'afficher le tablau d'entiers sous forme d'etats futurs 
		 */
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
			int liste;
			caseAutomate frame=
			ArrayList<etatAutomate> liste=new ArrayList<etatAutomate>();
			
			auto.automate(liste);
			System.out.println("nous allons afficher le tableau des actions \n");
			auto.to_string (1,2);
		}
}

		

		
		




