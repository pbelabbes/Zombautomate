package Model;

public abstract class Arme {
	
		//BASEBALL_BAT(5,0),
		//KATANA(10,0);
		private int value;
		private int usure;

		public Arme(int value, int usure){
			setValue(value);
			setUsure(usure);
		}


		public String toString(){
			return "("+value+","+usure+")";
		}

		public int getValue() {
			return this.value;
		}
		public int getUsure() {
			return this.usure;
		}
		public void setUsure(int usure) {
			this.usure=usure;
			return ;
		}
		public void setValue(int value) {
			this.value=value;
			return ;
		}
}
