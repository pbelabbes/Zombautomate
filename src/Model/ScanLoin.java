/**
 * 
 */
package Model;

import java.awt.Point;

/**
 * @author zennouche
 *
 */
public class ScanLoin extends Condition {

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
	
	public void setParameter(char parameter){
		this.parameter=parameter;
	}
	
	public void setRayon(int rayon){
		this.rayon=rayon;
	}

	/* (non-Javadoc)
	 * @see Model.Condition2#execute(Model.Cell)
	 */

	
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
					    	diffy=(pN.y-cellule.getPosition().y);
					    	
					    	if(diffx>diffy){
					    		if(minE==0){minE=distance(map.getGrid()[p1.x][p1.y].getPosition(),cellule.getPosition());}
					    		else minE=Math.min(minE, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
					    	else {
					    		
					    		if(minN==0){minN=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
					    		else minN=Math.min(minN, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
						}	
					    	
					    if(position_dans_map(position.x+j,position.y+i, map.getHeight(),map.getWidth()) && map.getGrid()[position.x+j][position.y+i].getDecor()==decor){
					    	diffx=position.x+j-position.x;
					    	diffy=position.y+i-position.y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(map.getGrid()[position.x+j][position.y+i].getPosition(),position);}
					    		else minO=Math.min(minO, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
					    	else {
					    		if(minS==0){minS=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position); }
					    		else minS=Math.min(minS, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
					    }
					
					    if(position_dans_map(position.x+j,position.y-i, map.getHeight(),map.getWidth()) && map.getGrid()[position.x+j][position.y-i].getDecor()==decor){
					    	diffx=position.x+j-position.x;
					    	diffy=position.y-i-position.y;
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(map.getGrid()[position.x+j][position.y-i].getPosition(),position); }
					    		else minO=Math.min(minO, distance(map.getGrid()[position.x+j][position.y-i].getPosition(),position));
					    	}
					    	else {
					    		if(minN==0){minN=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
					    		else minN=Math.min(minN, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
					    }
					    if(position_dans_map(position.x-j,position.y+i, map.getHeight(),map.getWidth()) && map.getGrid()[position.x-j][position.y+i].getDecor()==decor){
					    	diffx=position.x-j-position.x;
					    	diffy=position.y+i-position.y;
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(map.getGrid()[position.x+j][position.y+i].getPosition(),position);}
					    		else minE=Math.min(minE, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
					    	else {
					    		if(minS==0){minS=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
					    		else minS=Math.min(minS, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
					    	}
					    }
					}
				}
			 return direction(minN,minE,minS,minO);
		}
	  	
	}
	public boolean execute(Cell cellule) {
		
		
		
		// TODO Auto-generated method stub
		return false;
	}

}
