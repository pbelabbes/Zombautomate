package Model;

public class caseAutomate {
	public Condition condition;
	public Action action;
	public int etat_futur;
	public int priorite;
    public char direction;
	
	caseAutomate(int etat_futur,Action action,Condition condition,int priorite,char direction){
		this.etat_futur=etat_futur;
		this.action=action;
		this.condition=condition;
		this.direction=direction;
		this.priorite=priorite;
	}


	public String toString(Action action){
		return ""+action;
	}

	public int etat_futur() {
		return this.etat_futur;
		
	}
	public Action action() {
		return this.action;
		
	}
	
}
