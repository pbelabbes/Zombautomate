/**
 * 
 */
package Model;

import java.awt.Point;

/**
 * @author zennouche
 *
 */
public class ScanProche extends Condition2 {

	/**
	 * 
	 */
	//Attributs
	

	private String cible;
	private Decor decor;
	
	
	
	
	
	//Constructeurs 
	
	public ScanProche(String cible) {
		
		this.cible=cible;
		this.decor=null;// TODO Auto-generated constructor stub
	}
	public ScanProche(Decor decor) {
			
		this.decor=decor;
		this.cible=null;// TODO Auto-generated constructor stub
	}

	
	
	//getters 
	
	public String getCible(){
		return this.cible;
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
	/* (non-Javadoc)
	 * @see Model.Condition2#execute()
	 */
	@Override
	private int nb_cible(Cell c1,Cell c2, Cell c3, Cell c4){
		int nb=0;
		if(cible!=null){
			
		 switch (cible){
			case "ennemi":	if(c1.getEntity_on() instanceof Survivor &&
							c1.getEntity_on().getPlayer()!=personnage.getPlayer() ) nb++;  break;	
				
							if(c2.getEntity_on() instanceof Survivor &&
						       c2.getEntity_on().getPlayer()!=personnage.getPlayer()) nb++; break;	
							
							if(c3.getEntity_on() instanceof Survivor &&
						       c3.getEntity_on().getPlayer()!=personnage.getPlayer() ) nb++; break;	
							
							if(c4.getEntity_on() instanceof Survivor &&
							   c4.getEntity_on().getPlayer()!=personnage.getPlayer() ) nb++; break;
							   
			case "zombie": if(c1.getEntity_on() instanceof Zombie ) nb++; break;	
							if(c2.getEntity_on() instanceof Zombie ) nb++; break;	
							if(c3.getEntity_on() instanceof Zombie ) nb++; break;	
							if(c4.getEntity_on() instanceof Zombie ) nb++; break;
			default: break;				
		 }	 
		}
		return nb;
	}
	
	
	//(*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)

public boolean execute(char result){
		int nb;
		//on recupere les coordonnées des differentes cellules adjacentes
		Cell cN=getTargetedCell('N');
		Cell cS=getTargetedCell('S');
		Cell cE=getTargetedCell('E');
		Cell cO=getTargetedCell('O');
		
		
   if(cible!=null){		
		nb=nb_cible(cN,cS,cE,cO);		
		//dans ce cas on est sur que nb=1
	 if(nb == 1 ) {
			if(cible=="ennemi"){
	
				if( cN.getEntity_on().getPlayer()!=personnage.getPlayer() ){
					return ('N'==result);
				}
				else if(cS.getEntity_on().getPlayer()!=personnage.getPlayer()){
					return ('S'==result);
				}
				else if( cE.getEntity_on().getPlayer()!=personnage.getPlayer() ){
					return ('E'==result);
				}
				else if(cO.getEntity_on().getPlayer()!=personnage.getPlayer()){
					return ('O'==result);
				}
			}
			else if(cible=="zombie"){

				if( cN.getEntity_on() instanceof Zombie ){
					return ('N'==result);
				}
				else if(cS.getEntity_on() instanceof Zombie){
					return ('S'==result);
				}
				else if( cE.getEntity_on() instanceof Zombie ){
					return ('E'==result);
				}
				else if(cO.getEntity_on() instanceof Zombie ){
					return ('O'==result);
				}
			}
		}
	
   }
	else if(decor!=null){	
			if( cN.getDecor()==decor ){
			
				return ('N'==result);
			}
			else if(cS.getDecor()==decor){
				return ('S'==result);
			}
			else if( cE.getDecor()==decor ){
				return ('E'==result);
			}
			else if(cO.getDecor()==decor){
				return ('O'==result);
			}
		
}
	else return  (((char) nb +'0')==result);
		// TODO Auto-generated method stub
		
}
