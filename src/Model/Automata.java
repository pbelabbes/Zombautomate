package Model;

/**
 * La classe Automata représente le comportement d'un joueur ou d'un zombie
 * les attributs etats et inputs correspondent respectivement au nombre d'etats de l'automate et au nombre de conditions/transitions maximums par état soit à la largeur et à la hauteur de caseautomate 
 * states est le tableau dans lequel sera sauvé le comportement du personnage
 * position est le point ou se trouve la case en haut à gauche de states
 */

import java.util.ArrayList;
import java.awt.Point;



public class Automata {
	//Atributs

	private int etats;
	private int inputs;
	private caseAutomate[][] states;
	private Point position; 
		
	//getter & setter
	public int getEtats() {
		return etats;
	}
	public void setEtats(int width){
		this.etats=width;
	}
	
	public int getInputs() {
		return inputs;
	}
	public void setInputs(int height){
		this.inputs=height;
	}
	
	public caseAutomate[][] getStates() {
		return states;
	}
	public void setStates(caseAutomate[][] states) {
		this.states = states;
	}
	
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	
	//constructeurs:
	/**
	 * 
	 * @param height
	 * @param width
	 */
	public Automata(int height, int width){

			setEtats(width);
			this.inputs=height;
			states=new caseAutomate[height][width];			
		}

	//Methodes
	/**
	 * La fonction ajoute ets utilisée pour construire un automate
	 * @param etat
	 */
	private void ajoute(etatAutomate etat){	
		if(getInputs()==0 ||getEtats()==0) return;
		int j=0;
					   
			while ((states[etat.etat_courant][j]!=null) && (j<getInputs())){
				j++;		   
			}
			states[etat.etat_courant][j]=etat.square;		   
			
		}
	
	
	/**
	 * 
	 * @param liste
	 */
	public void automate(ArrayList<etatAutomate> liste){
		etatAutomate etat;
		for(int i=0;i<liste.size();i++){
			etat=liste.get(i);
			ajoute(etat);
		}
	}
	
	/**
	 * la fonction qui retourne les proportions de l'automate 
	 * @return
	 */
	public Point proportion(){
	    int i,j;
	    i=getPosition().x+getInputs();
	    j=getPosition().y+getEtats();
	    Point p=new Point(i,j);

	    return p;
	}
		
	/**
	 * La fonction qui permet d'afficher le tablau d'entiers sous forme d'etats futurs 
	 * @param width
	 * @param height
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
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Automata auto= new Automata(1,2);
		//etatAutomate et_au=new etatAutomate();
		//caseAutomate frame= ;
		List<etatAutomate> liste = new ArrayList<etatAutomate>();
		//liste={};
		auto.automate(liste);
		System.out.println("nous allons afficher le tableau des actions \n");
		auto.to_string (1,2);
	}
}

		

		
		




