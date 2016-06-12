/**
 * 
 */
package Model;

import java.awt.Point;
import java.util.ArrayList;


/**
 * @author zennouche
 *
 */
public class Moteur {

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
		return etat_max +1;
	}
	
	/**
	 * permet de récuperer la hauteur maximale à envisager pour placer les automates sur la carte
	 * @param j1 Joueur 1
	 * @param j2 Joueur 2
	 * @return Hauteur maximale parmi tous les automates des joueurs
	 */
	private static int getHmax(Player j1, Player j2)
	{
		int h_max = 0;

		ArrayList<Character> lC1 = j1.getEntities();
		ArrayList<Character> lC2 = j2.getEntities();
		
		
		//2 for each
		for(Character c : lC1) 
		{
		 if(c.getAutomata().getInputs() > h_max) h_max = c.getAutomata().getInputs(); 

		}

		for(Character c : lC2) 
		{
		 if(c.getAutomata().getInputs() > h_max) h_max = c.getAutomata().getInputs(); 

		}
		return h_max;
	}

	
	/**
	 * permet de récuperer la largeur maximale à envisager pour placer les automates sur la carte
	 * @param j1 Joueur 1
	 * @param j2 Joueur 2
	 * @return Largeur maximale parmi tous les automates des joueurs
	 */
	private static int getWmax(Player j1, Player j2)
	{
		ArrayList<Character> lC1 = j1.getEntities();
		ArrayList<Character> lC2 = j2.getEntities();

		int w_max = 0;

		//2 for each
		for(Character c : lC1) 
		{
		 if(c.getAutomata().getEtats() > w_max) w_max = c.getAutomata().getEtats();

		}

		for(Character c : lC2) 
		{
		 if(c.getAutomata().getEtats() > w_max) w_max = c.getAutomata().getEtats();

		}
		return w_max;
	}
	
	/**
	 * Permet de calculer la liste des points qui définiront les emplacement des automates
	 * @param j1 Joueur1
	 * @param j2 Joueur2
	 * @return Liste de points espacés des dimensions maximales d'un automate.
	 */
	public static ArrayList<Point> getList_coords_automatas(Player j1, Player j2)
	{
		
		ArrayList<Point> lP = new ArrayList<Point>(); //résultat qui sera retourné
		
		int nb_charact = j1.getEntities().size() + j2.getEntities().size(); //nombre de personnages dont on devra placer l'automate sur la carte
		int compteur = nb_charact; //Comptera le nombre de coordonnées créées pour ne pas faire une liste inutilement trop longue de coordonnées
		
		//dimensions maximales d'un automate
		int h_max = getHmax(j1,j2);
		int w_max = getWmax(j1,j2);
		
		//On place à peu près autant d'automates sur la hauteur que sur la largeur de la carte (sqrt permet d'équilibrer ces 2 dimensions)
		for(int x = 0 ; (int) x < Math.sqrt(nb_charact) +1 ; x++)
		{
			for(int y = 0 ; (int) y < Math.sqrt(nb_charact) +1 ; y++)
			{
				//On calcule les coordonnées des automates sur la carte
				int x_reel = (int) (x*w_max+  5*Math.random() +5*x); //on prend en compte le décalage aléatoire en ajoutant en plus : 5*x
				int y_reel = (int) (x*h_max+  5*Math.random() +5*y);
				
				if(compteur>0) lP.add(new Point(x_reel,y_reel));
				compteur--; //On créé autant de points qu'il y a d'automates à placer. d'où le compteur
			}
		}
		
		return lP;
	}
	
	/**
	 * A utiliser comme un constructeur de map
	 * @param j1 Joueur 1
	 * @param j2 Joueur 2
	 * @return une map dont les coordonnées conviennent au joueurs
	 */
	public static Map create_map(Player j1, Player j2)
	{
		ArrayList<Character> l1 = j1.getEntities();
		ArrayList<Character> l2 = j2.getEntities();
		
		int nb_character = l1.size() + l2.size();
		
		//dimensions de la map : (voir la fonction get_list_coords_automatas(..) pour comprendre les coordonnées de la map 
		int x_map = (int) ((5+getWmax(j1,j2))*(Math.sqrt(nb_character)+2));
		int y_map = (int) ((5+getHmax(j1,j2))*(Math.sqrt(nb_character)+2));
		return new Map(x_map,y_map);
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
		
		//geurrier
		//hauteur = 16
		//nb etat = 2
		
		//faremeur 
		//hauteur = 34 
		//nb etat = 3
		ArrayList<ArrayList<transfer>> liste=fichier.read("../Zombautomate/ocaml/equipe.xml");
		Player Zombi = new Player(0, "Zombies", 10 );
		
		System.out.println("\n\n\n debut");
//		System.out.println(Integer.toString(width(liste.get(0))));
//		System.out.println(Integer.toString(height(liste.get(0))));
//		System.out.println(Integer.toString(width(liste.get(1))));
//		System.out.println(Integer.toString(height(liste.get(1))));
//		
//		System.out.println( Integer.toString(CreateEntities(Zombi, liste).size()));
		
		//ajout de l'automate au perso ZOmbi ) 
		Zombi.setEntities(CreateEntities(Zombi, liste));
	
		
		
		System.out.println("\n\n\n\n\nTest alexandre \n\n\n\n");
		ArrayList<ArrayList<transfer>> liste1=fichier.read("../Zombautomate/ocaml/equipe1.xml");
		ArrayList<ArrayList<transfer>> liste2=fichier.read("../Zombautomate/ocaml/equipe2.xml");
		

		Player j1 = new Player(1 ,"Joueur 1", 10);
		Player j2 = new Player(1 ,"Joueur 2", 10);
		
		j1.setEntities(CreateEntities(j1,liste1));
		j2.setEntities(CreateEntities(j2,liste2));
		
		ArrayList<Character> lC = j1.getEntities();
		lC.addAll(j2.getEntities());
		
		Map carte = create_map(j1,j2);
		carte.init_map();
		carte.setAutomatas(lC, getList_coords_automatas(j1,j2));
		
	}

}