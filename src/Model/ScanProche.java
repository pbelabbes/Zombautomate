/**
 * 
 */
package Model;

import java.awt.Point;

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
	
	public ScanProche(String cible,char parameter) {
		
		this.cible=cible;
		this.decor=null;// TODO Auto-generated constructor stub
		this.parameter=parameter;
	}
	public ScanProche(Decor decor,char parameter) {
			
		this.decor=decor;
		this.cible=null;// TODO Auto-generated constructor stub
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
	
	private int nb_cible(Cell c1,Cell c2, Cell c3, Cell c4, Cell cellule){
		int nb=0;
		 System.out.println("debut \n");

		if(cible!=null){
			
		 
			if(cible== "ennemi"){
						    System.out.println("la ou il faut \n");
							if(c1.getEntity_on()!=null &&c1.getEntity_on() instanceof Survivor &&
							c1.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 	
							   System.out.println("after first if \n");
							 if(c2.getEntity_on()!=null &&c2.getEntity_on() instanceof Survivor &&
						       c2.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()) nb++; 	
							 System.out.println("apres 2eme \n");
						     if(c3.getEntity_on()!=null &&c3.getEntity_on() instanceof Survivor &&
						       c3.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 
						       System.out.println("apres 3eme  \n");
						       System.out.println(c4.getEntity_on()!=null);
						       System.out.println(c4.getEntity_on() instanceof Survivor );
						       System.out.println(c4.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer());
						    if(c4.getEntity_on()!=null && c4.getEntity_on() instanceof Survivor &&
							   c4.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 
						    System.out.println(nb +"\n");
			}   
			else if(cible== "zombie"){
							if(c1.getEntity_on() instanceof Zombie ) nb++; 	
							if(c2.getEntity_on() instanceof Zombie ) nb++; 	
							if(c3.getEntity_on() instanceof Zombie ) nb++; 	
							if(c4.getEntity_on() instanceof Zombie ) nb++; 
						
		 }	 
		}
		else{ System.out.println("dans le else \n");
		      System.out.println(c1.getDecor()==decor);
			if(c1.getDecor()!=null &&c1.getDecor()==decor ) { System.out.println("dans le else \n"); nb++;}
			if(c2.getDecor()!=null &&c2.getDecor()==decor ){  System.out.println("dans le else \n");nb++;}		
			if(c3.getDecor()!=null &&c3.getDecor()==decor){ System.out.println(c3.getDecor()==null);nb++;}
			if(c4.getDecor()!=null &&c4.getDecor()==decor ) {nb++;}
			System.out.println(nb+"\n ");
			return nb;
		}
		 
		System.out.println(nb+"\n ");

      return nb;
	}
	
	
	//(*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul ou le nombre d'ennemis sinon *)

	
	/**
	 * Cette fonction permet de renvoyer un booleen indiquant on trouve bien un element de decor 
	 * ou un charactere a l'une des cases adjacentes 
	 * Utilisation: si l'on cherche un decor on set la variable decor et sinon on set la deuxieme variable et
	 * on met le resultat attendu dans parameter
	 */
public boolean execute(Cell cellule){
		int nb=0;
		//on recupere les coordonnées des differentes cellules adjacentes
		Cell cN=getTargetedCell('N',cellule);
		Cell cS=getTargetedCell('S',cellule);
		Cell cE=getTargetedCell('E',cellule);
		Cell cO=getTargetedCell('O',cellule);
		
		nb=nb_cible(cN,cS,cE,cO,cellule);
   if(cible!=null && (cellule.getEntity_on()!=null)){		
				
		//dans ce cas on est sur que nb=1
	 if(nb == 1 ) {
			if(cible=="ennemi"){
	
				if( cN.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ){
					return ('N'==parameter);
				}
				else if(cS.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
					return ('S'==parameter);
				}
				else if( cE.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ){
					return ('E'==parameter);
				}
				else if(cO.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
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
	 	 
	else if(decor!=null){
	
	
			if( cN.getDecor()==decor ){
				System.out.println(nb);

				return ('N'==parameter);
			}
			else if(cS.getDecor()==decor){
				return ('S'==parameter);
			}
			else if( cE.getDecor()==decor ){
				return ('E'==parameter);
			}
			else if(cO.getDecor()==decor){
				return ('O'==parameter);
			}
		
	}
	
   }		
  }
   return  (((char) nb +'0')==parameter);
   // TODO Auto-generated method stub
}

}
