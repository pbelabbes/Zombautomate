/**
 * 
 */
package Model;

import java.util.ArrayList;


/**
 * @author zennouche
 *
 */
public class main {

	/**
	 * 
	 */

	
	
	
	private int height(ArrayList<transfer> automata){
//		ArrayList<transfer> dejavu= new ArrayList<Integer>();
//		transfer El;
		int taille = width(automata) ;
		int nbtransition[] = new int[taille] ;
		int max = 0 ; 
		for (int i =0 ; i< taille ; i ++){
			nbtransition[i] = 0 ;
			
		}
		for(transfer el : automata){
			nbtransition[el.getEtat_courant()]++;
			if (nbtransition[el.getEtat_courant()]>max){
				max = nbtransition[el.getEtat_courant()] ;
			}
		}
		return max ;
	}
	
	private int width(ArrayList<transfer> automata){
		//ArrayList<int> dejavu= new ArrayList<int>();
		//transfer El;
		int etat_max = automata.get(0).getEtat_courant();
		for(transfer el : automata){
			if (el.getEtat_courant()> etat_max){
				etat_max = el.getEtat_courant();
			}
			
		}
		return etat_max;
	}
	public main() {
		ArrayList<ArrayList<transfer>> liste=new ArrayList<ArrayList<transfer>> ();
		/*1/-On lit le fichier xml et on renvoie la liste des personnages ainsi que leur
		 * automate
		 * 2/-on initialise la map 
		 * 3/on lance le jeu avec l'ordonnanceur jusqu'a ce que un payer perd
		 * 
		 */
		//on recupere la liste de liste 
		XMLReader fichier = new XMLReader() ; 
		liste=fichier.read();
		//on creer les personnages ainsi que les automates correpsondants
		
		for(int i=0;i<liste.size();i++){
			for(int j=0;j<liste.get(i).size();j++){
				
				
				
			}
		}
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
