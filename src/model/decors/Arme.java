package model.decors;
/**
 * 
 * Cette classe permet de définir une arme
 * Cette dernière est défibie par sa puissance (value) et son usure 
 *
 */
public abstract class Arme {
	
	//Attribut
	private int value;
	private int usure;
	
	
	//constructeur
	public Arme(int value, int usure){
		setValue(value);
		setUsure(usure);
	}
	
	
	//Méthodes
	
	/**
	 * pour afficher la valeur (=puissance) d'une arme et son usure 
	 */
	public String toString(){
		return "("+value+","+usure+")";
	}
	
	//Getter & Setter
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value=value;
		return ;
	}
	
	public int getUsure() {
		return this.usure;
	}
	
	public void setUsure(int usure) {
		this.usure=usure;
		return ;
	}

}
