/**
 * 
 */
package Model;

/**
 * @author zennouche
 *
 */
public class Et extends Condition2 {

	Condition2 c1;
	Condition2 c2;
	/**
	 * 
	 */
	public Et(Condition2 c1, Condition2 c2) {
		this.c1=c1;
		this.c2=c2;
	}

	@Override
	public boolean execute() {
		boolean b1,b2;
		b1=c1.execute();
		b2=c2.execute();
		return (b1&&b2);
	
	}


}
