package Model;

public class caseAutomate {
	Condition condition;
	Action action;
	int etat_futur;
	
	
	caseAutomate(int etat_futur,Action action,Condition condition){
		this.etat_futur=etat_futur;
		this.action=action;
		this.condition=condition;
	}
}
