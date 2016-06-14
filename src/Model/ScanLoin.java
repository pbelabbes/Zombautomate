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
	
	
	
public ScanLoin(String cible, char parameter) {
		
		this.cible=cible;
		this.decor=null;
		this.parameter=parameter;
		this.rayon=0;
	}
	public ScanLoin(Decor decor,char parameter) {
			
		this.decor=decor;
		this.cible=null;
		this.parameter=parameter;
		this.rayon=0;
	}

	
	
	//getters 
	
	public String getCible(){
		return this.cible;
	}
	
	public void setParameter(char parameter){
		this.parameter=parameter;
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
	
	//fonction auxiliaire utilisée par celle d'apres 
	private char direction(int N,int E,int S,int O){
		int min=1000000000;
		if(N!=0 && E!=0 && S!=0 && O!=0){
				min=Math.min(Math.min(Math.min(N, E), S), O);
				if(min==0)return '0';
				else if(min==N)return 'N';
				else if(min==S)return 'S';
				else if(min==E)return 'E';
				else return 'O';
		}
		if(N!=0)min=N;
		if(E!=0)min=E;
		if(O!=0)min=O;
		if(S!=0)min=S;
		if(N!=0)min=Math.min(N,min);
		if(E!=0)min=Math.min(E,min);
		if(O!=0)min=Math.min(O,min);
		if(S!=0)min=Math.min(S,min);
		if(N==min)return 'N';
		if(E==min)return 'E';
		if(O==min)return 'O';
		if(S==min)return 'S';
		return '0';
		
	}
	
	/**
	 * La fonction scanloin permet de savoir dans quelle direction se trouve un character recherché
	 * @param rayon défini la visibilité d'un joueur: le nombre de case autour du personnage où on va vérifier la présence d'un character.
	 * @param pers le type de personnage que l'on recherche
	 * @param map la carte du jeu
	 * @param position la position du joueur sur la carte 
	 * @return N, E, S, O selon la direction du joueur le proche
	 */
	
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
		int mapheight = cellule.getEntity_on().getMap().getHeight();
		int mapwidth = cellule.getEntity_on().getMap().getWidth();
		pN=new Point(cellule.getPosition());
		pS=new Point(cellule.getPosition());
		pE=new Point(cellule.getPosition());
		pO=new Point(cellule.getPosition());
		//System.out.println("avant le for");
		for(int i=0;i<rayon;i++){
			//System.out.println("apres le premier for");
			for(int j=0;j<(rayon-i);j++){
				//System.out.println("apres le deuxieme for");
				pN.x=(cellule.getPosition().x-j+mapwidth)%mapwidth;
	    		pN.y=(cellule.getPosition().y-i+mapheight)%mapheight;
	    		pS.x=(cellule.getPosition().x+j)%mapwidth;
	    		pS.y=(cellule.getPosition().y+i)%mapheight;
	    		pE.x=(cellule.getPosition().x+j)%mapwidth;
	    		pE.y=(cellule.getPosition().y-i+mapheight)%mapheight;
	    		pO.x=(cellule.getPosition().x-j+mapwidth)%mapwidth;
	    		pO.y=(cellule.getPosition().y+i)%mapheight;
				if(decor!=null){	
		    	//System.out.println("je suis dans decor");
	
//on test si la cellule en question !=null et aussi si le decor correspondant est celui rechrché
					
						//System.out.println( cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getDecor()==Decor.BASEBALL_BAT);
						if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y]!=null&& cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getDecor()==decor){

						//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
					    	diffx=Math.abs(pN.x-cellule.getPosition().x);
					    	diffy=Math.abs(pN.y-cellule.getPosition().y);
				    		System.out.println("je suis dans le premier 0 if");
				    		
					    	if(diffx>diffy){
					    		//System.out.println("je suis dans le premier 1if");
					    		if(minO==0){minO=distance(pN,cellule.getPosition());}
					    		else minO=Math.min(minO, distance(pN,cellule.getPosition()));
					    	}
					    	else {
					    		//System.out.println("je suis dans le premier if");
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
						}	
				    	//System.out.println(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]==null);

					    if(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]!=null&& cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getDecor()==decor){
					    	diffx=Math.abs(pS.x-cellule.getPosition().x);
					    	diffy=Math.abs(pS.y-cellule.getPosition().y);
					    	System.out.println("je suis dans le deuxieme  if");
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pS,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pS,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
					
				    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y]!=null &&cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getDecor()==decor){
					    	diffx=Math.abs(pO.x-cellule.getPosition().x);
					    	diffy=Math.abs(pO.y-cellule.getPosition().y);
					    	System.out.println("je suis dans le troisieme if");
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
					    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
					    	}
					    	else {
					    		if(minN==0){minN=distance(pO,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pO,cellule.getPosition()));
					    	}
					    }
					    if(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y]!=null &&cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getDecor()==decor){
					    	diffx=Math.abs(pE.x-cellule.getPosition().x);
					    	diffy=Math.abs(pE.y-cellule.getPosition().y);
					    	System.out.println("je suis dans le fourth if");
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    		if(minS==0){minS=distance(pE,cellule.getPosition());}
					    		else minS=Math.min(minS, distance(pE,cellule.getPosition()));
					    	}
					    }
					}
				if(cible=="ennemi"){
			    	//System.out.println("la ou je suis censé etre ");
					//System.out.println(i);
					 
							if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y]!=null && cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on() instanceof Survivor&& 
									cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){

								//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
							    	diffx=Math.abs(pN.x-cellule.getPosition().x);
							    	diffy=Math.abs(pN.y-cellule.getPosition().y);
						    		System.out.println("je suis dans le premier 0 if");
						    		
							    	if(diffx>diffy){
							    		//System.out.println("je suis dans le premier 1if");
							    		if(minO==0){minO=distance(pN,cellule.getPosition());}
							    		else minO=Math.min(minO, distance(pN,cellule.getPosition()));
							    	}
							    	else {
							    		//System.out.println("je suis dans le premier if");
							    		if(minN==0){minN=distance(pN,cellule.getPosition());}
							    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
							    	}
								}	
						    	//System.out.println(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]==null);

							    if(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]!=null && cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on() instanceof Survivor&& 
							    		cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
							    	diffx=Math.abs(pS.x-cellule.getPosition().x);
							    	diffy=Math.abs(pS.y-cellule.getPosition().y);
							    	//System.out.println("je suis dans le deuxieme  if");
							    	if(diffx>diffy){
							    		
							    		if(minE==0){minE=distance(pS,cellule.getPosition());}
							    		else minE=Math.min(minE, distance(pS,cellule.getPosition()));
							    	}
							    	else {
							    		//System.out.println("pkkkkkkkkkkkkkkkkkk");
							    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
							    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
							    	}
							    }
							
						    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y]!=null && cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Survivor&& 
						    		cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
							    	diffx=Math.abs(pO.x-cellule.getPosition().x);
							    	diffy=Math.abs(pO.y-cellule.getPosition().y);
							    	//System.out.println("je suis dans le troisieme if");
							    	if(diffx>diffy){
							    		
							    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
							    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
							    	}
							    	else {
							    		if(minN==0){minN=distance(pO,cellule.getPosition());}
							    		else minN=Math.min(minN, distance(pO,cellule.getPosition()));
							    	}
							    }
							    if(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y]!=null && cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on() instanceof Survivor&& 
							    		cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on().getPlayer()!=cellule.getEntity_on().getPlayer()){
							    	diffx=Math.abs(pE.x-cellule.getPosition().x);
							    	diffy=Math.abs(pE.y-cellule.getPosition().y);
							    	System.out.println("je suis dans le fourth if");
							    	System.out.println(pE.toString());
							    	System.out.println(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on().player!=cellule.getEntity_on().getPlayer());
							    	if(diffx>diffy){
							    		
							    		if(minE==0){minE=distance(pE,cellule.getPosition());}
							    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
							    	}
							    	else {
							    		//System.out.println("whyyyyyyyyyyyyyyyy");
							    		if(minS==0){minS=distance(pE,cellule.getPosition());}
							    		else minS=Math.min(minS, distance(pE,cellule.getPosition()));
							    	}
							    }
							}
				else if(cible=="zombie"){
					

						if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y]!=null && cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on() instanceof Zombie){

							//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
						    	diffx=Math.abs(pN.x-cellule.getPosition().x);
						    	diffy=Math.abs(pN.y-cellule.getPosition().y);
					    		System.out.println("je suis dans le premier 0 if");
					    		
						    	if(diffx>diffy){
						    		//System.out.println("je suis dans le premier 1if");
						    		if(minO==0){minO=distance(pN,cellule.getPosition());}
						    		else minO=Math.min(minO, distance(pN,cellule.getPosition()));
						    	}
						    	else {
						    		//System.out.println("je suis dans le premier if");
						    		if(minN==0){minN=distance(pN,cellule.getPosition());}
						    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
						    	}
							}	
					    	//System.out.println(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]==null);

						    if(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]!=null && cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on() instanceof Zombie){
						    	diffx=Math.abs(pS.x-cellule.getPosition().x);
						    	diffy=Math.abs(pS.y-cellule.getPosition().y);
						    	//System.out.println("je suis dans le deuxieme  if");
						    	if(diffx>diffy){
						    		
						    		if(minE==0){minE=distance(pS,cellule.getPosition());}
						    		else minE=Math.min(minE, distance(pS,cellule.getPosition()));
						    	}
						    	else {
						    		//System.out.println("pkkkkkkkkkkkkkkkkkk");
						    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
						    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
						    	}
						    }
						
					    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y]!=null && cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Zombie){
						    	diffx=Math.abs(pO.x-cellule.getPosition().x);
						    	diffy=Math.abs(pO.y-cellule.getPosition().y);
						    	if(diffx>diffy){
						    		
						    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
						    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
						    	}
						    	else {
						    		if(minN==0){minN=distance(pO,cellule.getPosition());}
						    		else minN=Math.min(minN, distance(pO,cellule.getPosition()));
						    	}
						    }
						    if(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y]!=null && cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on() instanceof Zombie){
						    	diffx=Math.abs(pE.x-cellule.getPosition().x);
						    	diffy=Math.abs(pE.y-cellule.getPosition().y);
						    	System.out.println("je suis dans le fourth if");
						    	System.out.println(pE.toString());
						    	System.out.println(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on().player!=cellule.getEntity_on().getPlayer());
						    	if(diffx>diffy){
						    		
						    		if(minE==0){minE=distance(pE,cellule.getPosition());}
						    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
						    	}
						    	else {
						    		if(minS==0){minS=distance(pE,cellule.getPosition());}
						    		else minS=Math.min(minS, distance(pE,cellule.getPosition()));
						    	}
						    }
				    }
				else if(cible =="allie"){
					 
					if( cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y]!=null && cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on() instanceof Survivor&& 
							cellule.getEntity_on().getMap().getGrid()[pN.x][pN.y].getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()){

						//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
					    	diffx=Math.abs(pN.x-cellule.getPosition().x);
					    	diffy=Math.abs(pN.y-cellule.getPosition().y);
				    		System.out.println("je suis dans le premier 0 if");
				    		
					    	if(diffx>diffy){
					    		//System.out.println("je suis dans le premier 1if");
					    		if(minO==0){minO=distance(pN,cellule.getPosition());}
					    		else minO=Math.min(minO, distance(pN,cellule.getPosition()));
					    	}
					    	else {
					    		//System.out.println("je suis dans le premier if");
					    		if(minN==0){minN=distance(pN,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pN,cellule.getPosition()));
					    	}
						}	
				    	//System.out.println(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]==null);

					    if(cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y]!=null && cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on() instanceof Survivor&& 
					    		cellule.getEntity_on().getMap().getGrid()[pS.x][pS.y].getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()){
					    	diffx=Math.abs(pS.x-cellule.getPosition().x);
					    	diffy=Math.abs(pS.y-cellule.getPosition().y);
					    	//System.out.println("je suis dans le deuxieme  if");
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pS,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pS,cellule.getPosition()));
					    	}
					    	else {
					    		//System.out.println("pkkkkkkkkkkkkkkkkkk");
					    		if(minS==0){minS=distance(pS,cellule.getPosition()); }
					    		else minS=Math.min(minS, distance(pS,cellule.getPosition()));
					    	}
					    }
					
				    if(cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y]!=null && cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on() instanceof Survivor&& 
				    		cellule.getEntity_on().getMap().getGrid()[pO.x][pO.y].getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()){
					    	diffx=Math.abs(pO.x-cellule.getPosition().x);
					    	diffy=Math.abs(pO.y-cellule.getPosition().y);
					    	//System.out.println("je suis dans le troisieme if");
					    	if(diffx>diffy){
					    		
					    		if(minO==0){minO=distance(pO,cellule.getPosition()); }
					    		else minO=Math.min(minO, distance(pO,cellule.getPosition()));
					    	}
					    	else {
					    		if(minN==0){minN=distance(pO,cellule.getPosition());}
					    		else minN=Math.min(minN, distance(pO,cellule.getPosition()));
					    	}
					    }
					    if(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y]!=null && cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on() instanceof Survivor&& 
					    		cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on().getPlayer()==cellule.getEntity_on().getPlayer()){
					    	diffx=Math.abs(pE.x-cellule.getPosition().x);
					    	diffy=Math.abs(pE.y-cellule.getPosition().y);
					    	System.out.println("je suis dans le fourth if");
					    	System.out.println(pE.toString());
					    	System.out.println(cellule.getEntity_on().getMap().getGrid()[pE.x][pE.y].getEntity_on().player!=cellule.getEntity_on().getPlayer());
					    	if(diffx>diffy){
					    		
					    		if(minE==0){minE=distance(pE,cellule.getPosition());}
					    		else minE=Math.min(minE, distance(pE,cellule.getPosition()));
					    	}
					    	else {
					    	
					    		if(minS==0){minS=distance(pE,cellule.getPosition());}
					    		else minS=Math.min(minS, distance(pE,cellule.getPosition()));
					    	}
					    }
					}
									
				}
		}
	
		System.out.println(minN);
		System.out.println(minE);
		  System.out.println(minS);
		  System.out.println(minO);
		
		  return parameter==direction(minN,minE,minS,minO);
  
	}

	}


