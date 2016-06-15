package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

//import java.util.ArrayList;
/**
 * La classe Map représente le terrain de jeu
 * Elle est caractérisée par sa largeur et sa hauteur ainsi que son tableau de cellule(=cases)
 * 
 *
 */




//Remarque d'alexandre : je me suis permis de retirer width et height car ils ne sont finalement pas nécessaires.
//J'ai aussi retiré les setters car ils ne modifiaient pas "grid"
//j'ai aussi ajouté un setter pour initialiser une case précise de grid (surtout utile pour débug)

public class Map extends Observable{
//	private int width,height;
	private Cell[][] grid;
	
	//Constructeurs 
	// Petite modif d'alex : la carte peut maintenant être rectangulaire.
	public Map(int width, int height) {
		 grid=new Cell[width][height];
		 setChanged();
		 notifyObservers(this.grid);

/*		 this.width=width;
		 this.height = height;
*/		 

	}
	
	//Getter et Setter
	public int getWidth() {
		return grid.length;
	}

	/*
	public void setWidth(int width) {
		this.width = width;
	}
*/
	public int getHeight() {
		return grid[0].length;
	}
	
	/*
	public void setHeight(int height) {
		this.height = height;
	}
*/

	public Cell[][] getGrid() {
		return grid;
	}
	
	
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
		setChanged();
		notifyObservers(this.grid);
	}


	public void setCell(Cell cell)
	{
		grid[cell.getPosition().x][cell.getPosition().y] = cell;
	}
	
	/**
	 * met des décors aléatoires sur la carte. Les automates ne sont pas encore placés
	 */
	public void init_map()
	{
		for(int x = 0; x < this.getWidth(); x++)
		{
			for(int y = 0 ; y < this.getHeight() ; y++)
			{
				grid[x][y] = new Cell(new Point(x,y));
			}
		}
	}
	
	/**
	 * 
	 * @param a : un automate à placer sur la carte
	 * @param pos : la position à laquelle l'automate est placé (coin supérieur gauche)
	 */
	private void setAutomata(Automata a, Point pos, Character perso)
	{
//		a.to_string();

		for(int x= pos.x ; x < a.getEtats()+pos.x; x++)
		{
			for(int y = pos.y ; y < a.getInputs()+pos.y ; y++)
			{
				CaseAutomate case_tempo = a.getStates()[x-pos.x][y-pos.y];
				if(case_tempo!=null)
				{	
					
//					int x_r = x%gridheight; //x_reel (c'est un tor)
//					int y_r = y%gridwidth; //y_reel (c'est un to
	
//					System.out.println(this.grid[x][y].getOwned_by() != null);
					
//					System.out.printf("x = %d ; y = %d    ;    x_r = %d ;  y_r = %d\n",x,y,x_r,y_r);

//					System.out.printf("x = %d ; y = %d \n",x,y);
//					System.out.printf("x-pos.x = %d ; y-pos.y = %d\n", x-pos.x,y-pos.y);
					this.grid[x][y].setOwned_by(perso);
					this.grid[x][y].setDecor(case_tempo.getAction().getDecor());
					this.grid[x][y].setPosition(pos);
					

				}
			}
		}
	}

	public void setAutomatas(ArrayList<Character> lC, ArrayList<Point> lP)
	{
		//on lit la liste dans un ordre aléatoire
		Random rand_index = new Random();
		
		ArrayList<Character> lC_copie = new ArrayList<Character>();
		lC_copie.addAll(lC);
		
		int i = 0;
		rand_index.setSeed(System.currentTimeMillis());
		
		while(lC_copie.size()>0)
		{	
			Character char_tempo = lC_copie.get(rand_index.nextInt(lC_copie.size()));
			this.setAutomata(char_tempo.getAutomata(),lP.get(i),char_tempo);
			lC_copie.remove(char_tempo);
			i++;
		}
			
	}
	
	/**
	 * Place un personnage à la position indiquée
	 * @param c personnage à placer
	 * @param p position du personnage
	 */
	public void set_charact(Character c, Point p)
	{
		c.setMap(this);
		c.setCell(this.getGrid()[p.x][p.y]);
		this.getGrid()[p.x][p.y].setEntity_on(c);
	}
	
	/**
	 * @param lC -> Liste des personnages à placer
	 * Place aléatoirement les personnages sur la map
	 */
	public void set_charact_position(ArrayList<Character> lC)
	{
		for(Character c : lC) 
		{
			int x,y;
			do
			{
				x =(int) (this.getWidth()*Math.random());
				y =(int) (this.getHeight()*Math.random());
			}
			while(this.getGrid()[x][y].getEntity_on()!=null);
			this.set_charact(c,new Point(x,y));
		}
	}
	
	/**
	 * affiche les positions de la carte qui sont reliées à un automate
	 */
	public void print_automatas()
	{
		for(int y = 0 ; y<getHeight() ; y++)
		{
			for(int x = 0 ; x<getWidth() ; x++)
			{
				if(this.grid[x][y].getOwned_by() != null)
					System.out.printf("[%d][%d]"+this.grid[x][y].getOwned_by().toString() + " ",x,y);
				else System.out.printf("[%d][%d] : libre (decor = "+this.grid[x][y].getDecor() + ") " ,x,y);
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Fait apparaitre un zombie sur la cellule donnée. Il faut cependant être certain qu'aucune entité n'est présent sur l'emplacement indiqué
	 * @param cellule cellule d'apparition du zombie
	 * @param p0 Joueur 0 (controle les zombies)
	 * @param a automate de zombie
	 * @param map carte sur laquelle se déroule la partie
	 */
	public void pop_zombie(Cell cellule, Player p0, Automata a,Map map)
	{
		Zombie new_z = new Zombie(p0,a,map);
		cellule.setEntity_on(new_z);
	}
	
	public void print_map()
	{
		int h=getHeight();
		int w=getWidth();
		for (int y = 0 ; y< h ; y++)
		{
			for(int x = 0 ; x < w ; x++)
			{
				if(grid[x][y].getEntity_on()!=null)		System.out.printf("%d",grid[x][y].getEntity_on().getPlayer().getId());
				else 	System.out.print(".");
			}
			System.out.println();
		}
		System.out.println();
	}
}
