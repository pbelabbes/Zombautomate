package Model;

public enum Nourriture {
RABBIT(10),
APPLE(30);
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