package Model;
import java.util.ArrayList;

public class Ordonnanceur {
	
	ArrayList<Character> List;
	ArrayList<Character> Ordre;
	
	// Rempli la liste "Ordre" de manière aléatoire à partir des personnage de la liste "List"
	
	public void next_player (){
		
		for (int k=0; k == List.size(); k++){
			
		if(List.size() >= 1){
			int i = (int) (Math.random() * (List.size() - 1));
			Ordre.add(List.get(i));
			List.remove(List.get(i));	
			}
		}
	}
	
	// Pour chaque personnage de la liste "Ordre", si le personnage est vivant, on lui fait effectuer sa prochaine action
	
	public void next_move() {
		for (int i = 0 ; i == Ordre.size(); i++)
		if (Ordre.get(i).is_alive == true){ 
			
		
		}
	}
}

			

