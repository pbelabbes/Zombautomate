package Model;

/**
 * @author pierrebelabbes
 *
 */

import java.util.ArrayList;
import java.awt.Point;


public class Automata {
//Atributs

		private int etats;
		private int inputs;
		
		caseAutomate[][] states;
        Point position; 
	
     
//constructeurs:
           //getter & setter
        public int getEtats() {
			return etats;
		}

		public int getInputs() {
			return inputs;
		}

		public caseAutomate[][] getStates() {
			return states;
		}
		public void setStates(caseAutomate[][] states) {
			this.states = states;
		}

		public void setEtats(int width){
			this.etats=width;
		}
		
		public void setInputs(int height){
			this.inputs=height;
		}
		
		public Point getPosition() {
			return position;
		}
		public void setPosition(Point position) {
			this.position = position;
		}

		//constructeurs:
		public Automata(int height, int width){

			setEtats(width);
			this.inputs=height;
			states=new caseAutomate[height][width];			
		}

		//Methodes
		
		private void ajoute(etatAutomate etat){
			
		if(getInputs()==0 ||getEtats()==0) return;
		int j=0;
					   
			while ((states[etat.etat_courant][j]!=null) && (j<getInputs())){
				j++;		   
			}
			states[etat.etat_courant][j]=etat.square;		   
			
		}
		
		
		public void automate(ArrayList<etatAutomate> liste){
		
			etatAutomate etat;
			for(int i=0;i<liste.size();i++){
				etat=liste.get(i);
				ajoute(etat);
			}
		}

	  //la fonction qui retourne les proportions de l'automate 
		
		public Point proportion(){
		    Point p;
		    int i,j;
		    i=getPosition().x+getInputs();
		    j=getPosition().y+getEtats();
		    p=new Point(i,j);
		    
		    return p;
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
			//etatAutomate et_au=new etatAutomate();
			//caseAutomate frame= ;
			ArrayList<etatAutomate> liste = new ArrayList<etatAutomate>();
			
			auto.automate(liste);
			System.out.println("nous allons afficher le tableau des actions \n");
			auto.to_string (1,2);
		}
}

		

		
		




