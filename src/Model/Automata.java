package Model;

/**
 * @author pierrebelabbes
 *
 */

import java.util.ArrayList;


public class Automata {
//Atributs
		int etats;
		int inputs;
		
		caseAutomate[][] actions;
		int[][] states;

	
//constructeurs:
		Automata(int nb_etats){
			this.etats=nb_etats;
			//le nombre de nos entrees est fixe a 8
			this.inputs=8;
			transition(nb_etats);
			action(nb_etats);
			
		}
		
		private void transition (int taille){
			
			 states=new int[8][taille];
			
		}
		
		private void action(int taille){
			 actions=new Action[8][taille];
		}
		
		
		//Methodes
		
		//la liste a donner en parametre est de type :
		//{{0.1.POMME,1},.......}
		public void automate(ArrayList<etatAutomate> liste,int nb_etat){
		
			etatAutomate etat;
			transition(nb_etat);
			action(nb_etat);
			
			for (int i=0;i<liste.size();i++){
					etat=liste.get(i);
				  	states[etat.condition][etat.etat_courant]=etat.etat_futur;
					actions[etat.condition][etat.etat_courant]=etat.action;
				}
			}

		
		public void to_string_etats (int taille){
		
		//car le nombre d'entrees est de 8 pour tous les automates
		for (int i=0;i<8;i++){
		     for (int j=0;j<taille;j++){
		    	 System.out.println(this.states[i][j]+",");
		     } 
		     System.out.println("\n");
		}
		}
		
		public void to_string_actions (int taille){
			
			//car le nombre d'entrees est de 8 pour tous les automates
			for (int i=0;i<8;i++){
			     for (int j=0;j<taille;j++){
			    	 System.out.println(this.actions[i][j]+",");
			     } 	
			     System.out.println("\n");
			}
			}
		public static void main(String[] args){
			Automata auto= new Automata(3);
			etatAutomate et=new etatAutomate();
			et.action=Action.RAMASSER;
			et.condition=3;
			et.etat_futur=1;
			et.etat_courant=0;
			ArrayList<etatAutomate> liste=new ArrayList<etatAutomate>();
			liste.add(et);
			et.etat_courant=1;
			et.etat_futur=0;
			et.condition=3;
			liste.add(et);
			et.etat_courant=2;
			et.condition=6;
			et.etat_futur=2;
			liste.add(et);
			et.action=Action.ARROSER;
			et.condition=4;
			et.etat_courant=1;
			
			
			
				//{{0,3,RAMASSER,1},{1,2,RAMASSER,0},{1,4,ARROSER,2},{2,6,RAMASSER,2}};
			
			auto.automate(liste,3);
			System.out.println("nous allons afficher le tableau des actions \n");
			auto.to_string_actions (3);
			System.out.println("nous allons afficher le tableau des states \n");
			auto.to_string_etats (3);
		}
}

		

		
		




