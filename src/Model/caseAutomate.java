package Model;

public class caseAutomate {
	private Condition condition;
	private Action action;
	private int etat_futur;
	private int priorite;
    private char direction;
	
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
	public Condition getCondition()
	{
		return this.condition;
	}
	
}
