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

	private static ArrayList<Character> CreateEntities(Player J , ArrayList<ArrayList<transfer>> L){
		ArrayList<Character> res  = new ArrayList<Character>() ;
		Automata aut ; 
		caseAutomate[][] remplirautomate ; 
		int hauteur,largeur, k;
		
		//parcours de tous les automates.
		for (int i = 0 ; i< L.size() ; i++){
			hauteur = height(L.get(i)) ;
			largeur= width(L.get(i)); 

			aut = new Automata(0, largeur, hauteur);
			remplirautomate = new caseAutomate[largeur][hauteur ];
			
			//initialisation du tableau remplirautomate
			for(int x = 0 ; x < largeur ; x++){
				for (int z = 0 ; z < hauteur ; z ++)
					remplirautomate[x][z]= null ;;
			}
			
			
			//parcours de toutes les transitions
			for(transfer tr : L.get(i)){
				k = 0 ;
				// on se place sur le nombre de 
				while (remplirautomate[tr.getEtat_courant()][k] != null ) {
					k++;
				}
				
				remplirautomate[tr.getEtat_courant()][k] = new caseAutomate(tr.getEtat_futur(), tr.getAction(), tr.getCondition(), tr.getPriority(), tr.getDirection());
				
			}
			aut.setStates(remplirautomate);
			//res.add(new Character(J , aut ) ) ;
			
			if (J.getId() == 0 )
				res.add(new Zombie(J,aut,null));
			else 
				res.add(new Survivor(J, aut, null));
			 
		}
		
		return res;
	}
	
	//calcule la taille pour un automate
	private static int height(ArrayList<transfer> automata){
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
	
	//calcule la largeur d'un automate.
	private static int width(ArrayList<transfer> automata){
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
	
	//main
	public static void test() {
		ArrayList<ArrayList<transfer>> liste=new ArrayList<ArrayList<transfer>> ();
		/*1/-On lit le fichier xml et on renvoie la liste des personnages ainsi que leur
		 * automate
		 * 2/-on initialise la map 
		 * 3/on lance le jeu avec l'ordonnanceur jusqu'a ce que un payer perd
		 * 
		 */
		//on recupere la liste de liste 
		XMLReader fichier = new XMLReader() ;
		
		//TODO remplir le path
		liste=fichier.read("/home/zennouche/Documents/semestre6/PLA/exemple.xml");
		//on creer les personnages ainsi que les automates correpsondants
		Player Zombi = new Player(0, "Zombies", 10 );
		Zombi.setEntities(CreateEntities(Zombi, liste));
		
		//TODO remplir le path
		liste = fichier.read("/");
		Player j1 = new Player(0,"J1",10);
		j1.setEntities(CreateEntities(j1, liste));
		
		//TODO remplir le path
		liste = fichier.read("/");
		Player j2 = new Player(0,"J2",10);
		j2.setEntities(CreateEntities(j2, liste));
		


	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		XMLReader fichier = new XMLReader() ;
		
		ArrayList<ArrayList<transfer>> liste=fichier.read("/../ocaml/personnages_générés.xml");
		Player Zombi = new Player(0, "Zombies", 10 );
		
		Zombi.setEntities(CreateEntities(Zombi, liste));
		
		
		
		
		
	}

}
