 package Model;

import java.awt.Point;

public class Condition_fonction {
	
	
	
	
	//Methodes4
	//arg: une direction et une cible 
	/*
	 * on donne la position d'un character adjacent si il y'en a un 
	 */
	
	//Attention, les arguments devraient fonctionner de la meme maniere pour toutes les fonctions (éviter de prendre parfois une cellule et parfois un point par exemple)
	public boolean presence(char direction , Player joueur , Point position,Map map){
	
		Point p=new Point(position);
		boolean b;
		switch (direction){
		case 'N': p.y=p.y-1;break;
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;		
		}
		
		if(position_dans_map(p.x,p.y, map.getHeight(),map.getWidth()) && (map.getGrid()[p.x][p.y].getEntity_on()!=null)){
					
			//si ils appartiennent pas au meme joueuer alors ils sont ennemies et pas alliés
						b=(map.getGrid()[p.x][p.y].getEntity_on().getPlayer() != joueur) ;
						System.out.println(map.getGrid()[p.x][p.y].getEntity_on().getPlayer().getId());	
						System.out.println(joueur.getId());
						System.out.println(b);
					    return b;
		}
		else return false;
	}

	
	
	public boolean presence(char direction , Zombie zombie , Point position,Map map){
		
		Point p=new Point(position);
		boolean b;
		switch (direction){
		case 'N': p.y=p.y-1;break;
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;		
		}
		
		if(position_dans_map(p.x,p.y, map.getHeight(),map.getWidth()) && (map.getGrid()[p.x][p.y].getEntity_on()!=null)){
						//si ils appartiennent pas au meme joueuer alors ils sont ennemies et pas alliés
						b=(map.getGrid()[p.x][p.y].getEntity_on() instanceof Zombie) ;
					    return b;
		}
		else return false;
	}


//la fonction renvoie le type de decor qu'il y'a a la case a cote qui est dans la direction "direction"
	
	public boolean presence(char direction , Decor d , Point position, Map map){
		Point p=new Point(position);
		switch (direction){
		case 'N': p.y=p.y-1;break;
		case 'S': p.y=p.y+1;break;
		case 'E': p.x=p.x+1;break;
		case 'O': p.x=p.x-1;break;
		}
		return map.getGrid()[p.x][p.y].getDecor()==d;
	}

	
	
	private int distance(Point position1, Point position2){		
	    return (int) Math.sqrt(Math.pow(((float)(position2.x-position1.x)),2.0)+Math.pow(((float)(position2.y-position1.y)),2.0));
	}
	

	//fonction auxiliaire utilisée par celle d'apres 
	private char direction(int N,int E,int S,int O){
		int min=10000000;
		if(N!=0 && E!=0 && S!=0 && O!=0){
			min=Math.min(Math.min(Math.min(N, E), S), O);
			if(min==0)return '0';
			else if(min==N)return 'N';
			else if(min==S)return 'S';
			else if(min==E)return 'E';
		}
		else {
			if(N!=0)min=N;
			if(S!=0)min=S;
			if(O!=0)min=O;
			if(E!=0)min=E;
			if(N!=0)min=Math.min(min, N);
			if(E!=0)min=Math.min(min, E);
			if(S!=0)min=Math.min(min, S);
			if(O!=0)min=Math.min(min, O);
			if(min==N)return 'N';
			else if(min==S)return 'S';
			else if(min==E)return 'E';
			else return 'O';
			
			}
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
	public char Scanloin(int rayon, Character pers, Map map, Point position){
		int minN=0,minS=0,minE=0,minO=0;
		int diffx,diffy;
		
		
		for(int i=0;i<rayon;i++){
			for(int j=0;j<(rayon-i);j++){
			    if(map.getGrid()[position.x-j][position.y-i].getEntity_on()==pers){
			    	diffx=position.x-j-position.x;
			    	diffy=position.y-i-position.y;
			    	if(diffx>diffy){
			    		
			    		if(minE==0){minE=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);
				    }
			    		else minE=Math.min(minE, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    	else {
			    		if(minN==0){minN=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);
				    }
			    		else minN=Math.min(minN, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    	
			    }	
			    if(map.getGrid()[position.x+j][position.y+i].getEntity_on()==pers){
			    	diffx=position.x+j-position.x;
			    	diffy=position.y+i-position.y;
			    	if(diffx>diffy){
			    		
			    		if(minO==0){minO=distance(map.getGrid()[position.x+j][position.y+i].getPosition(),position);}
			    		else minO=Math.min(minO, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    	else {
			    		if(minS==0){minS=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
			    		else minS=Math.min(minS, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    }
			
			    if(map.getGrid()[position.x+j][position.y-i].getEntity_on()==pers){
			    	diffx=position.x+j-position.x;
			    	diffy=position.y-i-position.y;
			    	if(diffx>diffy){
			    		
			    		if(minO==0){minO=distance(map.getGrid()[position.x+j][position.y-i].getPosition(),position);}
			    		else minO=Math.min(minO, distance(map.getGrid()[position.x+j][position.y-i].getPosition(),position));
			    	}
			    	else {
			    		if(minN==0){minN=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
			    		else minN=Math.min(minN, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    }
			    if(map.getGrid()[position.x-j][position.y+i].getEntity_on()==pers){
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
		return direction (minN,minE,minS,minO);
	}

	
	private boolean position_dans_map(int x,int y, int height , int width){
		
		return ((x<width) && (y<height));
						
	}
	
	
	/*
	 * Cette fonction petmet de Rechercher un element de decor dans un certain rayon et envoie soit le nombre 
	 * d'element de decor present si ce nombre >1 et sinon renvoie la direction du plus proche si il n'y a 
	 * qu'un exemplaire de cet element et sinon 0 
	 */
	public char Scanloin(int rayon, Decor decor, Map map, Point position){
		int minN=0,minS=0,minE=0,minO=0;
		int diffx,diffy;
		
		for(int i=0;i<rayon;i++){
			for(int j=0;j<(rayon-i);j++){
				//on verifie si la position est encore dans la map et aussi si le decor correspond a ce qu'on veut 
			   if(position_dans_map(position.x-j,position.y-i, map.getHeight(),map.getWidth()) && map.getGrid()[position.x-j][position.y-i].getDecor()==decor){
			    	diffx=position.x-j-position.x;
			    	diffy=position.y-i-position.y;
			    	if(diffx>diffy){
			    		
			    		if(minE==0){minE=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
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
			    		
			    		if(minO==0){System.out.println("le min a l'Ouest"+minO );minO=distance(map.getGrid()[position.x+j][position.y+i].getPosition(),position);}
			    		else minO=Math.min(minO, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    	else {
			    		if(minS==0){System.out.println("le min au Sud"+minS );minS=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position); }
			    		else minS=Math.min(minS, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    }
			
			    if(position_dans_map(position.x+j,position.y-i, map.getHeight(),map.getWidth()) && map.getGrid()[position.x+j][position.y-i].getDecor()==decor){
			    	diffx=position.x+j-position.x;
			    	diffy=position.y-i-position.y;
			    	if(diffx>diffy){
			    		
			    		if(minO==0){System.out.println(minO);minO=distance(map.getGrid()[position.x+j][position.y-i].getPosition(),position); }
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
			    		if(minS==0){System.out.println("le min au sud"+minS );minS=distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position);}
			    		else minS=Math.min(minS, distance(map.getGrid()[position.x-j][position.y-i].getPosition(),position));
			    	}
			    }
			}
		}
		System.out.println("le min a l'Ouest"+minO );
		System.out.println("le min au sud"+minS );
		System.out.println("le min au Nord"+minN );
		System.out.println("le min a l'est"+minE );
	   return direction(minN,minE,minS,minO);	
	}
	
	/*
	 * La fonction Et qui renvoie un booleen permet de faire un && entre deux booleen qui sont 
	 * le retour de deux fonctions Condition
	 */
	public boolean Et(boolean c1, boolean c2){
	
	return (c1 && c2);
		
	}
    
	/*
	 * La fonction Et qui renvoie un booleen permet de faire un || entre deux booleen qui sont 
	 * le retour de deux fonctions Condition
	 */
	public boolean Ou(boolean c1, boolean c2){
		return (c1|| c2);
	}
	
	// fonction auxiliaire pour les suivantes
	
	private boolean in_intervalle(Point deb, Point fin, Point p){
		boolean a,b;
		a=((p.x>=deb.x)&&(p.x<=fin.x));
		b=((p.y>=deb.y)&&(p.y<=fin.y));
	   return (a && b);
		
	}
	/*
	 * Cette fonction verifie la cellule qui est dan une direction donnée et renvoie un booleen indiquant si 
	 * a cette case la se trouve l'automate d'un allie ou non 
	 */
	public boolean CaseAlliee(char direction,Cell cell, Map map){
		Point p=new Point(cell.getPosition());
		boolean a,b;
		Cell ce;
		switch (direction){
		
		case 'N': p.y=p.y-1; break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		ce=map.getGrid()[p.x][p.y];
		
		if(ce.getOwned_by() !=null){
		 
			b=(ce.getOwned_by().getPlayer() == cell.getEntity_on().getPlayer());
			return (b ); 
		}
		else return false;   
			
			
	}
	
  /*
   * Cette fonction verifie si dans la case qui est dans la direction donnée appartient a un automate d'un 
   * Ennemi ou pas 	
   */
	
	public boolean CaseEnnemie(char direction, Cell cell,Map map){
		Point p=new Point(cell.getPosition());
		boolean a,b;
		Cell ce;
		switch (direction){
		
		case 'N': p.y=p.y-1; break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		ce=map.getGrid()[p.x][p.y];
		if(ce.getOwned_by()!=null){
			
			return (ce.getOwned_by().getPlayer()!= cell.getEntity_on().getPlayer() );
		}	
		else return false; 	
	}

	//il suffit de savoir que la cellule sur laquelle on est n
	public boolean CaseNeutre(char direction, Cell cell, Map map){
		Point p=new Point(cell.getPosition());
		boolean a;
		Cell ce;
		switch (direction){
		
		case 'N': p.y=p.y-1; break;
		
		case 'S': p.y=p.y+1;break;
		
		case 'E': p.x=p.x+1;break;
		
		case 'O': p.x=p.x-1;break;
		}
		
		ce=map.getGrid()[p.x][p.y];
		//on teste si l'automate de cette cellule n'appartient a aucun personnage ni zombie
		a=(ce.getOwned_by()==null);
		return (a);
		
	}

	
	public int nb_ennemis(Point p1,Point p2, Point p3, Point p4, Map map, Player player){
		int nb=0;
		if(map.getGrid()[p1.x][p1.y].getEntity_on() instanceof Survivor &&
		   map.getGrid()[p1.x][p1.y].getEntity_on().getPlayer()!=player ) nb++;
		
		if(map.getGrid()[p2.x][p2.y].getEntity_on() instanceof Survivor &&
	       map.getGrid()[p2.x][p2.y].getEntity_on().getPlayer()!=player ) nb++;
		
		if(map.getGrid()[p3.x][p3.y].getEntity_on() instanceof Survivor &&
	       map.getGrid()[p3.x][p3.y].getEntity_on().getPlayer()!=player ) nb++;
		
		if(map.getGrid()[p4.x][p4.y].getEntity_on() instanceof Survivor &&
		   map.getGrid()[p4.x][p4.y].getEntity_on().getPlayer()!=player ) nb++;
		
		return nb;
	}
	
	
	//(*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)
	public boolean ScanProche(Point position, Map map , char result , Character pers){
		int nb;
		//on recupere les coordonnées des differentes cellules adjacentes
		Point pN=new Point(position);pN.y=pN.y-1;
		Point pS=new Point(position);pN.y=pN.y+1;
		Point pE=new Point(position);pN.x=pN.x+1;
		Point pO=new Point(position);pN.x=pN.x-1;
		nb=nb_ennemis(pN,pS,pE,pO, map, map.getGrid()[position.x][position.y].getEntity_on().getPlayer());
		
		//dans ce cas on est sur que nb=1
	if(nb == 1) {
		if( map.getGrid()[pN.x][pN.y].getEntity_on().getPlayer()!=map.getGrid()[position.x][position.y].getEntity_on().getPlayer() ){
			return ('N'==result);
		}
		else if( map.getGrid()[pS.x][pS.y].getEntity_on().getPlayer()!=map.getGrid()[position.x][position.y].getEntity_on().getPlayer() ){
			return ('S'==result);
		}
		else if( map.getGrid()[pE.x][pE.y].getEntity_on().getPlayer()!=map.getGrid()[position.x][position.y].getEntity_on().getPlayer() ){
			return ('E'==result);
		}
		else {
			return ('O'==result);
		}
	}
	else return (((char) nb +'0')==result);

	}


	//fonction auxiliaire qui sert a la fonction d'apres 
	public int nb_decor(Point p1,Point p2, Point p3, Point p4, Map map,Decor decor){
	int nb=0;
	
	if(map.getGrid()[p1.x][p1.y].getDecor()==decor ) {	System.out.println("first if ");nb++;}
	
	if(map.getGrid()[p2.x][p2.y].getDecor()==decor ){	System.out.println("second if"); nb++;}
	
	if(map.getGrid()[p3.x][p3.y].getDecor()==decor){	System.out.println("second if"); nb++;}
	
	if(map.getGrid()[p4.x][p4.y].getDecor()==decor ) {	System.out.println("second if"); nb++;}
	
	return nb;
}


	//(*fonctionne de la meme maniere mais en ne regardant que les cases adjacentes (portée 1) au personnage. Retourne alors la direction d'un ennemi si il est seul et le nombre d'ennemis sinon *)
	public boolean ScanProche(Point position, Map map , char result , Decor decor){
	int nb;
	//on recupere les coordonnées des differentes cellules adjacentes
	Point pN=new Point(position);pN.y=pN.y-1;
	Point pS=new Point(position);pS.y=pS.y+1;
	Point pE=new Point(position);pE.x=pE.x-1;
	Point pO=new Point(position);pO.x=pO.x+1;
	
	nb=nb_decor(pN,pS,pE,pO, map, decor);
	
	System.out.println("le nombre dans le decor :" +nb+"\n");
	//dans ce cas on est autsur que nb=1
if(nb == 1) {
	System.out.println("nb vaut  1");
	if( map.getGrid()[pN.x][pN.y].getDecor()==decor){
		return ('N'==result);
	}
	else if( map.getGrid()[pS.x][pS.y].getDecor()==decor ){
		return ('S'==result);
	}
	else if( map.getGrid()[pE.x][pE.y].getDecor()==decor){
		return ('E'==result);
	}
	else {
		return ('O'==result);
	}
}
else { 	System.out.println("nb vaut  plus que 1");return (((char) nb +'0')==result);}

}

}



