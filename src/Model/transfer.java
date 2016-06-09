/**
 * 
 */
package Model;

//import com.sun.org.apache.xpath.internal.operations.String;

/**
 * @author Ombre Social
 *
 */
public class transfer {
	private int etat_courant; 
	private int etat_futur;
	private String action; 
	private String condition; 
	private String direction ;
	private int priority ; 
	
	public transfer(int etat_courant,String condition, String action , String direction, int priority, int etat_futur) {
		//super();
		this.etat_courant = etat_courant;
		this.etat_futur = etat_futur;
		this.action = action;
		this.condition = condition;
		this.direction = direction;
		this.priority = priority; 
	}
	
	
	public int getEtat_courant() {
		return etat_courant;
	}
	public void setEtat_courant(int etat_courant) {
		this.etat_courant = etat_courant;
	}
	public int getEtat_futur() {
		return etat_futur;
	}
	public void setEtat_futur(int etat_futur) {
		this.etat_futur = etat_futur;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	} 
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	
}
