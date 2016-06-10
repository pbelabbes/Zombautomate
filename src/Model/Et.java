package Model;

public class Et extends Condition2 {
	Condition2 c1;
	Condition2 c2;
	
	public Et(Condition2 c1, Condition2 c2){
		this.c1=c1;
		this.c2=c2;
	}
	
	public boolean estVrai(){
		boolean b1,b2;
		b1=c1.estVrai();
		b2=c2.estVrai();
		return (b1&&b2);
	}
}
