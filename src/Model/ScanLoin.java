/**
 * 
 */
package Model;

import java.awt.Point;

/**
 * @author zennouche
 *
 */
public class ScanLoin extends Condition2 {

	/**
	 * 
	 */
	private String cible;
	private Decor decor;
	private char parameter;
	private int rayon;
	
	
	
public ScanLoin(String cible) {
		
		this.cible=cible;
		this.decor=null;// TODO Auto-generated constructor stub
		this.parameter=' ';
		this.rayon=0;
	}
	public ScanLoin(Decor decor) {
			
		this.decor=decor;
		this.cible=null;// TODO Auto-generated constructor stub
		this.parameter=' ';
		this.rayon=0;
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
	
	public int getRayon(){
		return this.rayon;
	}
	//setters
	
	public void setCible(String cible){
		 this.cible=cible;
	}
	
	public void setDecor(Decor decor){
		this.decor=decor;
	}
	
/*	public void setParameter(char parameter){
		this.parameter=parameter;
	}
*/	
	public void setRayon(int rayon){
		this.rayon=rayon;
	}

	/* (non-Javadoc)
	 * @see Model.Condition2#execute(Model.Cell)
	 */
	private int distance(Point position1, Point position2){		
	    return (int) Math.sqrt(Math.pow(((float)(position2.x-position1.x)),2.0)+Math.pow(((float)(position2.y-position1.y)),2.0));
	}
	
	public boolean execute(Cell cellule){
		int minN=0,minS=0,minE=0,minO=0;
		Point pN,pS,pE,pO;
		int diffx,diffy;
		
		for(int i=0;i<rayon;i++){
			for(int j=0;j<(rayon-i);j++){
				pN.x%=cellule.getPosition().x-j;
	    		pN.y%=cellule.getPosition().y-i;
	    		pS.x%=cellule.getPosition().x+j;
	    		pS.y%=cellule.getPosition().y+i;
	    		pE.x%=cellule.getPosition().x-j;
	    		pE.y%=cellule.getPosition().y+i;
	    		pO.x%=cellule.getPosition().x+j;
	    		pO.y%=cellule.getPosition().y-i;
				if(decor!=null){		
				
						if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getDecor()==decor){
						//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
					    	diffx=pN.x-cellule.getPosition().x;
					    	diffy=pN.y-cellule.getPosition().y;
					    	
					    	if(diffx>diffy){
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
						}	
					    	
					    if( cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getDecor()==decor){
					    	diffx=pS.x-cellule.getPosition().x;
					    	diffy=pS.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pS,cellule.getPosition());}
					    		else minO=Math.min(minO, distance(pS,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
					
					    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getDecor()==decor){
					    	diffx=pO.x-cellule.getPosition().x;
					    	diffy=pO.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
					    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
					    	}
					    	else {
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
					    }
					    if( cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getDecor()==decor){
					    	diffx=pE.x-cellule.getPosition().x;
					    	diffy=pE.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition());}
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
					}
				if(cible=="ennemi"){
					if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() &&
							cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on() instanceof Survivor){
						//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
					    	diffx=pN.x-cellule.getPosition().x;
					    	diffy=pN.y-cellule.getPosition().y;
					    	
					    	if(diffx>diffy){
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
						}	
					    	
					    if(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() &&
								cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on() instanceof Survivor){
					    	diffx=pS.x-cellule.getPosition().x;
					    	diffy=pS.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pS,cellule.getPosition());}
					    		else minO=Math.min(minO, distance(pS,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
					
					    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() &&
								cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Survivor){
					    	diffx=pO.x-cellule.getPosition().x;
					    	diffy=pO.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
					    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
					    	}
					    	else {
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
					    }
					    if( cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer() &&
								cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Survivor){
					    	diffx=pE.x-cellule.getPosition().x;
					    	diffy=pE.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition());}
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
				}
				
				if(cible=="zombie"){
					if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on() instanceof Zombie){
						//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
					    	diffx=pN.x-cellule.getPosition().x;
					    	diffy=pN.y-cellule.getPosition().y;
					    	
					    	if(diffx>diffy){
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
						}	
					    	
					    if(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on() instanceof Zombie){
					    	diffx=pS.x-cellule.getPosition().x;
					    	diffy=pS.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pS,cellule.getPosition());}
					    		else minO=Math.min(minO, distance(pS,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
					
					    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Zombie){
					    	diffx=pO.x-cellule.getPosition().x;
					    	diffy=pO.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
					    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
					    	}
					    	else {
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
					    }
					    if( cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Zombie){
					    	diffx=pE.x-cellule.getPosition().x;
					    	diffy=pE.y-cellule.getPosition().y;
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition());}
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
				}
				}
		}
		  parameter=direction(minN,minE,minS,minO);
		  if(parameter!='0')return true; 
		  else return false;
	}

	}


