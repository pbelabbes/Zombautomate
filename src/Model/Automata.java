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

	private int etats;
	private int inputs;
	private caseAutomate[][] states;
	private Point position; 
	private int etat_courant;
	private caseAutomate caseAutomate;
	
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
	
	public caseAutomate getCA(){
		return this.caseAutomate;
	}
	
	
	//constructeurs:
	/**
	 * @param etat_courant
	 * @param width
	 * @param height
	 */
	//
	public Automata( int etat_courant,int width , int height){
			setEtats(width);
			this.inputs=height;
			this.etat_courant=etat_courant;
			states=new caseAutomate[height][width];			
		}

	
	public int getEtatCourant(){
		return this.etat_courant;
	
		
	}
	public void setEtatCourant(int etatCourant){
		this.etat_courant=etatCourant;
	}
		
	
	/**
	 * 
	 * @param Condition
	 * @return etat_futur
	 */
	public int getEtatFutur(Condition C){
		int j=0;
		
			while(states[etat_courant][j].getCondition()!=null && states[etat_courant][j].getCondition()!=C){
				j++;
			}
			if(states[etat_courant][j].getCondition()==C) 
				return states[etat_courant][j].getEtatfutur();
			else return -1;
	}
	
	//Methodes
	/**
	 * La fonction ajoute est utilisée pour construire un automate
	 * @param etat
	 */
	private void ajoute(transfer etat){	
		if(getInputs()==0 ||getEtats()==0) return;
		int j=0;
		caseAutomate ca=new caseAutomate(etat.getEtat_futur(),etat.getAction(), etat.getCondition(),etat.getPriority(),etat.getDirection());
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
	 * La fonction qui permet d'afficher le tableau d'entiers sous forme d'etats futurs 
	 * @param width
	 * @param height
	 */
	public void to_string(int width,int height){
		for (int i=0;i<height;i++){
		     for (int j=0;j<width;j++){
		    	 if(states[i][j]!=null){
		    		 System.out.println(this.states[i][j].getEtatfutur()+",");
		    	 }	 
		     } 
		     System.out.println("\n");
		}	
    }
	
	/*public static void main(String[] args){
		Automata auto= new Automata(0,2,2);
		int etat_futur;
		caseAutomate frame=new caseAutomate(7,Action.DROP,Condition.PRESENCE);
		caseAutomate frame2=new caseAutomate(2,Action.DROP,Condition.PRESENCE);
		etatAutomate et_au1=new etatAutomate(0,'N',0,frame);
		etatAutomate et_au2=new etatAutomate(1,'E',0,frame);
		etatAutomate et_au3=new etatAutomate(0,'S',0,frame2);
		ArrayList<etatAutomate> liste = new ArrayList<etatAutomate>();
		liste.add(et_au1);
		liste.add(et_au2);
		liste.add(et_au3);
		auto.automate(liste);
		System.out.println("nous allons afficher le tableau des actions \n");
		auto.to_string (2,2);
		etat_futur=auto.getEtatFutur(Condition.PRESENCE);
		System.out.println("l'etat futur avec condition= presence et etat_courant a 0 est:\n"+etat_futur);

		
	}
	*/
}

		

		
		




