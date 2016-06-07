package Model;

public class etatAutomate {
	int etat_courant;
	int condition;
	Action action;
	int etat_futur;

	//Constructeurs 
	etatAutomate(){
		this.etat_courant=-1;
		this.condition=-1;
		this.etat_futur=-1;
	}
}
