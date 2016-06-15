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
	private int id;
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
	

	@Override
	public boolean execute(Cell cellule)
	{
		Character charact = cellule.getEntity_on();
		rayon = charact.getSightRange();
		int id = charact.getPlayer().getId();

		switch(this.cible)
		{
		case "allie": this.id = id;  break; 
		case "zombie": this.id = 0;  break;  
		case "ennemi": this.id = id == 1? 2:1;  break; 
		default : ;
		}
		
		return scan(cellule);
	}

	/**
	 * effectue le scan sur un décor
	 * @param cellule case du personnage effectuant le scan
	 * @return résultat du scan en fonction de la cible recherchée
	 */
	private boolean scan(Cell cellule)
	{
		//on définit un point temporaire qui permet d'analyser les cases aux alentours
		Point point = new Point(cellule.getPosition());
		Point closest = null;

		Map map = cellule.getEntity_on().getMap();

		for(int x=0 ; x<rayon ; x++)
		{
			for(int y=0 ; y<rayon-x ; y++)
			{
				if(x!=0 || y!=0)
				{
					Point temp = check_sym(point, new Point(x,y) , map);
					if(closest == null) 
						closest=temp;
					else 
						if(temp != null && temp.distance(point)<closest.distance(point))   closest = temp;
				}
			}
		}
		return calc_retour(point, closest)==parameter;
	}

	/**
	 * calcule la valeur retournée par le scan
	 * @param point point ciblant la cellule du personnage
	 * @param closest cellule la plus proche vérifiant la condition 
	 * @return char indiquant la direction de la cible la plus proche ou '0' si pas de cible
	 */
	private char calc_retour(Point point, Point closest)
	{
		System.out.println("Closest = "+ closest+" et point = "+point);
		char res = '0';

		if(closest != null)
		{
			int diffx = closest.x - point.x;
			int diffy = closest.y - point.y;

			if(Math.abs(diffx)>Math.abs(diffy))	
				res = diffx>0? 'E':'O';
				else res = diffy>0? 'S':'N';
		}
		
		return res;
	}

	/**
	 * Permet de vérifier les 4 points equidistants au personnage à partir d'un point initial
	 * @param p point initial
	 * @param map carte sur laquelle on fait cette vérification
	 * @return point vérifiant la condition recherchée
	 */
	private Point check_sym(Point p, Point delta, Map map)
	{
		int h = map.getHeight();
		int w = map.getWidth();
		Point res = null;
		Cell[][] grid = map.getGrid();
		
		// les coordonnées calculées ne sont pas très claires mais en gros c'est le point décalé de delta, et on applique le tor sur le résultat
		int px = (p.x+delta.x+w)%w;
		int py = (p.y+delta.y+h)%h;
		if (check_point(grid[px][py])) res = new Point (px,py);

		px = (p.x+delta.x*-1 +w)%w;
		py = (p.y+delta.y+h)%h;
		if(check_point(grid[px][py])) res = new Point(px,py);

		px = (p.x+delta.x*-1 +w)%w;
		py = (p.y+delta.y*-1 +h)%h;
		if(check_point(grid[px][py])) res = new Point(px,py);

		px = (p.x+delta.x +w)%w;
		py = (p.y+delta.y*-1 +h)%h;
		if(check_point(grid[px][py])) res = new Point(px,py);

		return res;
	}


	/**
	 * permet de savoir si la cible se trouve sur la cellule donnée
	 * @param cellule cellule sur laquelle on vérifie la présence de la cible
	 * @return vrai si le point en question désigne une cellule comportant la cible faux sinon
	 */
	private boolean check_point(Cell cellule)
	{
		if(decor == null)
		{
			Character ent_on = cellule.getEntity_on();
			return ent_on != null && ent_on.getPlayer().getId() == id;
		}
		else
			return cellule.getDecor() == decor;
	}


}