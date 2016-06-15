package Model;

public class CaseAutomate {
	private Condition condition;
	private Action action;
	private int etat_futur;
	private int priorite;
    private char direction;
	
    /**
     * 
     * @param etat_futur
     * @param action
     * @param condition
     * @param priorite
     * @param direction
     */
	CaseAutomate(int etat_futur,Action action,Condition condition,int priorite,char direction){
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
/**
 * 
 * @return
 */
	public int getEtatfutur() {
		return this.etat_futur;
		
	}
	/**
	 * 
	 * @return
	 */
	public Action getAction() {
		return this.action;
		
	}
	public void setAction(Action action){
		this.action=action;
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