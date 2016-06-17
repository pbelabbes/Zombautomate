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
	int indice; //permet de savoir qui joue
	CaseAutomate case_auto;	//enregistre les infos sur l'action en cours
	int compteur_tour; //compte le nombre de tours effectués depuis le début de la partie
	
	/**
	 * Remplit la liste "Ordonnee" de manière aléatoire à partir des personnage de la liste "Liste"
	 * @param liste
	 */
	public Ordonnanceur(ArrayList<Character> liste)
	{
		List=liste;
		Ordonnee = null;
		indice = 0;
		case_auto = null;
		melanger();
		compteur_tour = 1;
	}
	
	/**
	 * @return personnage qui joue
	 */
	public Character getCharacter()
	{
		if(Ordonnee.size()>0)
			return Ordonnee.get(indice);
		else {
			System.out.println("Attention, l'ordonnanceur a mal été utilisé");
			return null;
		}
	}
	
	/**
	 * @return le tour de jeu
	 */
	public int getTurn()
	{
		return compteur_tour;
	}
	
	/**
	 * 
	 * @return Action effectuee par le personnage
	 */
	public Action getAction()
	{
		if(case_auto != null)
		{
			return case_auto.getAction();
		}
		else return null;
	}
	
	/**
	 * 
	 * @return direction dans laquelle se fait l'action
	 */
	public char getDirection()
	{
		if(case_auto != null)
			return case_auto.getDirection();
		else return 'U'; //Undefined
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
	
	
	public void next()
	{

		Character c = Ordonnee.get(indice);
		if(c.is_alive())
		{
			case_auto = c.getTransition();
			c.act(getAction(), getDirection());
		//	c.eat();
			
		}
		indice++;

		if(Ordonnee.size()<=indice)
		{
			melanger();
			indice = 0;
			compteur_tour++;
		}
	}
	
	/**
	 * Pour chaque personnage de la liste "Ordonnee", si le personnage est vivant, next_move lui fait effectuer sa prochaine action
	 */
	public void next_move() {

	for (int i = 0 ; i < Ordonnee.size(); i++)
		if (Ordonnee.get(i).is_alive())
		{ 	
//			(Ordonnee.get(i)).eat();
			(Ordonnee.get(i)).play();
		}
	}
	
}

