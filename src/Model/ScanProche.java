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
		if(cible!=null){
			
		 
			if(cible== "ennemi"){
							if(c1.getEntity_on() instanceof Survivor &&
							c1.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 	
				
							 if(c2.getEntity_on() instanceof Survivor &&
						       c2.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()) nb++; 	
							
						       if(c3.getEntity_on() instanceof Survivor &&
						       c3.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 
							
						    if(c4.getEntity_on() instanceof Survivor &&
							   c4.getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() ) nb++; 
			}   
			else if(cible== "zombie"){
							if(c1.getEntity_on() instanceof Zombie ) nb++; 	
							if(c2.getEntity_on() instanceof Zombie ) nb++; 	
							if(c3.getEntity_on() instanceof Zombie ) nb++; 	
							if(c4.getEntity_on() instanceof Zombie ) nb++; 
						
		 }	 
		}
		else if(decor!=null){
			if(c1.getDecor()==decor ) {nb++;}
			
			if(c2.getDecor()==decor ){ nb++;}
			
			if(c3.getDecor()==decor){ nb++;}
			
			if(c4.getDecor()==decor ) {nb++;}
			
			return nb;
		}
		return nb;
	}
	
	
	//(*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul ou le nombre d'ennemis sinon *)

public boolean execute(Cell cellule){
		int nb=0;
		//on recupere les coordonnées des differentes cellules adjacentes
		Cell cN=getTargetedCell('N',cellule);
		Cell cS=getTargetedCell('S',cellule);
		Cell cE=getTargetedCell('E',cellule);
		Cell cO=getTargetedCell('O',cellule);
		
		
   if(cible!=null && (cellule.getEntity_on()!=null)){		
		nb=nb_cible(cN,cS,cE,cO,cellule);		
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
		nb=nb_cible(cN,cS,cE,cO,cellule);	
			if( cN.getDecor()==decor ){
			
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
