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

/**
 * 
 * @param action
 * @return
 */
 
	public String toString(Action action){
		return ""+action;
	}

	public int getEtatfutur() {
		return this.etat_futur;
		
	}
	public Action getAction() {
		return this.action;
		
	}
	
	public char getDirection() {
		return this.direction;
		
	}
	public Condition getCondition()
	{
		return this.condition;
	}
	public int getPriorite(){
		return this.priorite;
	}
	
}
