/**

 * 
 */
package Model;

//import java.awt.Point;

/**
 * @author zennouche
 *
 */
public class ScanProche extends Condition {

	/**
	 * 
	 */
	//Attributs
	

	private String cible;
	private Decor decor;
	private char parameter;
	
	
	
	
	//Constructeurs 
	/**
	 * 
	 * @param cible
	 * @param parameter
	 */
	public ScanProche(String cible,char parameter) {
		
		this.cible=cible;
		this.decor=null;
		this.parameter=parameter;
	}
	public ScanProche(Decor decor,char parameter) {
			
		this.decor=decor;
		this.cible=null;
		this.parameter=parameter;
	}

	
	
	//getters 
	
	public String getCible(){
		return this.cible;
	}

	public char getParameter(){
		return this.parameter;
	}
	
	public Decor getDecor(){
		return this.decor;
	}
	
	//setters
	
	public void setCible(String cible){
		 this.cible=cible;
	}
	
	public void setDecor(Decor decor){
		this.decor=decor;
	}
	
	public void setParameter(char parameter){
		this.parameter=parameter;
	}
	/* (non-Javadoc)
	 * @see Model.Condition2#execute()
	 */
	/**
	 * Cette fonction permet de verifier le nombre de cible ou decor presents dans les cellules adjacentes 
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param cellule
	 * @return
	 */
	private int nb_cible(Cell c1,Cell c2, Cell c3, Cell c4, Cell cellule){
		int nb=0;

		if(cible!=null){
			
		 
			if(cible== "ennemi"){
						 
						  if(c1!=null && c1.getEntity_on()!=null &&c1.getEntity_on() instanceof Survivor &&
							  c1.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 							   
						   if(c2!=null &&c2.getEntity_on()!=null &&c2.getEntity_on() instanceof Survivor &&
						       c2.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()) nb++; 	
						   if(c3!=null && c3.getEntity_on()!=null &&c3.getEntity_on() instanceof Survivor &&
						       c3.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 
						   if(c4!=null && c4.getEntity_on()!=null && c4.getEntity_on() instanceof Survivor &&
							   c4.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 
			}  
			else if(cible== "allie"){
				 
				  if(c1!=null && c1.getEntity_on()!=null  &&
					  c1.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer() ) nb++; 							   
				   if(c2!=null &&c2.getEntity_on()!=null &&
				       c2.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()) nb++; 	
				   if(c3!=null && c3.getEntity_on()!=null &&
				       c3.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer() ) nb++; 
				   if(c4!=null && c4.getEntity_on()!=null &&
					   c4.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer() ) nb++; 
	}  
			else if(cible== "zombie"){
							if(c1.getEntity_on() instanceof Zombie ) nb++; 	
							if(c2.getEntity_on() instanceof Zombie ) nb++; 	
							if(c3.getEntity_on() instanceof Zombie ) nb++; 	
							if(c4.getEntity_on() instanceof Zombie ) nb++; 
						
		 }	 
		}
		else{ 
		   
			if(c1!=null &&c1.getDecor()==decor ) { nb++;}
			if(c2!=null &&c2.getDecor()==decor ){nb++;}		
			if(c3!=null &&c3.getDecor()==decor){nb++;}
			if(c4!=null &&c4.getDecor()==decor ) {nb++;}
		
			return nb;
		} 

      return nb;
	}
	
	/**
	 * Cette fonction permet de renvoyer un booleen indiquant si on trouve bien un element de decor 
	 * ou un charactere a l'une des cases adjacentes 
	 * Utilisation: si l'on cherche un decor on set la variable decor et sinon on set la deuxieme variable et
	 * on met le resultat attendu dans parameter
	 */
public boolean execute(Cell cellule){
		int nb=0;
		Cell cN=getTargetedCell('N',cellule);
		Cell cS=getTargetedCell('S',cellule);
		Cell cE=getTargetedCell('E',cellule);
		Cell cO=getTargetedCell('O',cellule);
		
	 nb=nb_cible(cN,cS,cE,cO,cellule);
     if(nb == 1 ) {	
		if(cible!=null){			
			if(cible=="ennemi"){
	
				if(cN!=null &&cN.getEntity_on()!=null&& cN.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ){
					return ('N'==parameter);
				}
				else if(cS!=null &&cS.getEntity_on()!=null&& cS.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
					return ('S'==parameter);
				}
				else if( cE!=null &&cE.getEntity_on()!=null&& cE.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ){
					return ('E'==parameter);
				}
				else if(cO!=null &&cO.getEntity_on()!=null&& cO.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
					return ('O'==parameter);
				}
			}
			else if(cible=="zombie"){

				if( cN.getEntity_on() instanceof Zombie ){
					return ('N'==parameter);
				}
				else if(cS.getEntity_on() instanceof Zombie){
					return ('S'==parameter);
				}
				else if( cE.getEntity_on() instanceof Zombie ){
					return ('E'==parameter);
				}
				else if(cO.getEntity_on() instanceof Zombie ){
					return ('O'==parameter);
				}
			}
			else if(cible=="allie"){
	
				if( cN!=null &&cN.getEntity_on()!=null&& cN.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer() ){
					return ('N'==parameter);
				}
				else if(cS!=null &&cS.getEntity_on()!=null&& cS.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()){
					return ('S'==parameter);
				}
				else if(cE!=null &&cE.getEntity_on()!=null&&  cE.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer() ){
					return ('E'==parameter);
				}
				else if(cO!=null &&cO.getEntity_on()!=null&& cO.getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()){
					return ('O'==parameter);
				}
			}	
		}
	        	 
	if(decor!=null){
		System.out.println("dans decor");
	
			if(cN!=null && cN.getDecor()==decor ){
				System.out.println(parameter);
        
				return ('N'==parameter);
			}
			else if(cS!=null &&cS.getDecor()==decor){
				return ('S'==parameter);
			}
			else if(cE!=null && cE.getDecor()==decor ){
				return ('E'==parameter);
			}
			else if(cO!=null &&cO.getDecor()==decor){
				return ('O'==parameter);
			}
		
	}
	 
  }
  	   return  (((char) nb +'0')==parameter);
}

}
