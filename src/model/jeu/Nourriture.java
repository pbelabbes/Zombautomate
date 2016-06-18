package model.jeu;

public enum Nourriture {
RABBIT(18),
APPLE(8);
private int value;

private Nourriture(int value){
	this.value=value;
}

public String toString(){
	return ""+value;
}

public int getvalues() {
	return this.value;
}
}