package Model;

/**
 * La classe Automata représente le comportement d'un joueur ou d'un zombie
 * les attributs etats et inputs correspondent respectivement au nombre d'etats de l'automate et au nombre de conditions/transitions maximums par état 
 * soit à la largeur et à la hauteur de caseautomate 
 * states est le tableau dans lequel sera sauvé le comportement du personnage
 * position est le point ou se trouve la case en haut à gauche de states
 */

import java.util.ArrayList;
import java.awt.Point;



public class Automata {
	//Atributs
	
	//nb d'états.
	private int etats;
	//nb d'entrée.
	private int inputs;
	private CaseAutomate[][] states;
	private Point position; 
	private int etat_courant;
	private CaseAutomate caseAutomate;
	
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
	
	public CaseAutomate[][] getStates() {
		return states;
	}
	public void setStates(CaseAutomate[][] states) {
		this.states = states;
	}
	
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public CaseAutomate getCA(){
		return this.caseAutomate;
	}
	
	
	public int getEtatCourant(){
		return this.etat_courant;	
	}
	public void setEtatCourant(int etatCourant){
		this.etat_courant=etatCourant;
	}
	
	//constructeurs:
	/**
	 * @param etat_courant
	 * @param width
	 * @param height
	 */
	//
	public Automata( int etat_courant,int width , int height){
		if (etat_courant<0 || width<0 || height<0){
			System.out.println("initialisation fausse entier negatif");
		}
		else{
			setEtats(width);
			this.inputs=height;
			this.etat_courant=etat_courant;
			this.states=new CaseAutomate[width][height];
		}
	}

		
	//Methodes
	/**
	 * 
	 * @param Condition
	 * @return etat_futur
	 */
	public int getEtatFutur(Condition C){
		int j=0;
		
		while(states[etat_courant][j].getCondition()!=null && states[etat_courant][j].getCondition()!=C)
		{
			j++;
		}
		if(states[etat_courant][j].getCondition()==C) 
			return states[etat_courant][j].getEtatfutur();
		else return -1;
	}
	

	/**
	 *Cette fonction est utilisée pour construire un automate
	 * @param etat
	 */
	private void ajoute(transfer etat){	
		if(getInputs()==0 ||getEtats()==0) return;
		int j=0;
		CaseAutomate ca=new CaseAutomate(etat.getEtat_futur(),etat.getAction(), etat.getCondition(),etat.getPriority(),etat.getDirection());
			while ((states[etat.getEtat_courant()][j]!=null) && (j<getInputs())){
				j++;		   
			}
			states[etat.getEtat_courant()][j]=ca;		   
			
		}
	
	
	/**
	 * Cette fonction permet de remplir un automate a partir d'une liste
	 * @param liste
	 */
	public void automate(ArrayList<transfer> liste){
		transfer etat;
		if(liste!=null){
			for(int i=0;i<liste.size();i++){
				etat=liste.get(i);
				ajoute(etat);
			}
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
	 * La fonction qui permet d'afficher le tableau d'entiers sous forme d'actions
	 */
	public void to_string(){ 
		for (int y=0;y<states[0].length;y++){
		     for (int x=0;x<states.length;x++){
		    	 if(states[x][y]!=null){
		    		 System.out.printf("states[%d][%d] = "+states[x][y].getAction().toString(),x,y); 
		    	 }
		    	 else System.out.printf("states[%d][%d] = null",x,y);
			     System.out.println("\n");
		     } 
		     System.out.println("\n");
		}	

	}
}

		

		
		




