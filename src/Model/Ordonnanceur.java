package Model;
import java.util.ArrayList;




/**
 * 
 * @author skander
 *
 */
public class Ordonnanceur {
	
	ArrayList<Character> List;		//liste des entités
	ArrayList<Character> Ordonnee;	//liste mélangée
	
	
	/**
	 * Remplit la liste "Ordonnee" de manière aléatoire à partir des personnage de la liste "Liste"
	 * @param liste
	 */
	public Ordonnanceur(ArrayList<Character> liste)
	{
		List=liste;
		Ordonnee = null;
	}
	
  /**
   * La fonction mélanger récupère une liste de personnage (list) et melange aléatoirement ces personnages dans une nouvelle liste (liste_temp) 
   */
	public void melanger (){
		
		ArrayList<Character> liste_tempo = new ArrayList<Character>();
		liste_tempo.addAll(List); 
		//On ne souhaite pas modifier la liste initiale donc on créé une copie de cette liste.
		
		
		Ordonnee = new ArrayList<Character>();
		
		while(liste_tempo.size()>0)
		{
			int i = (int) (Math.random() * liste_tempo.size());
			Ordonnee.add(liste_tempo.get(i));
			liste_tempo.remove(liste_tempo.get(i));	
		}
	}
	
	
	/**
	 * Pour chaque personnage de la liste "Ordonnee", si le personnage est vivant, next_move lui fait effectuer sa prochaine action
	 */
	public void next_move() {

	for (int i = 0 ; i < Ordonnee.size(); i++)
		if (Ordonnee.get(i).is_alive())
		{ 	
			(Ordonnee.get(i)).eat();
			(Ordonnee.get(i)).play();
		}
	}
	
}

