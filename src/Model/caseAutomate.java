package Model;

public class caseAutomate {
	public Condition condition;
	public Action action;
	public int etat_futur;
	
	
	caseAutomate(int etat_futur,Action action,Condition condition){
		this.etat_futur=etat_futur;
		this.action=action;
		this.condition=condition;
	}
}
