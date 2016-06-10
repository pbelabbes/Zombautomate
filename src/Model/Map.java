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
		int gridheight = this.getHeight();
		int gridwidth = this.getWidth();
		for(int x=0+pos.x ; x < a.getEtats()+pos.x; x++)
		{
			for(int y = 0+pos.y ; y < a.getInputs()+pos.y ; y++)
			{
				int x_r = x%gridheight; //x_reel (c'est un tor)
				int y_r = y%gridwidth; //y_reel (c'est un tor)
				this.grid[x_r][y_r].setOwned_by(perso);
				this.grid[x_r][y_r].setDecor(a.getStates()[x-pos.x][y-pos.y].action().getDecor());
				this.grid[x_r][y_r].setPosition(pos);
			}
		}
	}

	public void setAutomatas(ArrayList<Character> lC, ArrayList<Point> lP) //TODO générer cette liste de points qui determine les positions des automates sur la map
	{
		//on lit la liste dans un ordre aléatoire
		Random rand_index = new Random();
		
		int i = 0;
		rand_index.setSeed(System.currentTimeMillis());
		
		while(lC!=null)
		{	
			Character char_tempo = lC.get(rand_index.nextInt(lC.size()));
			this.setAutomata(char_tempo.getAutomata(),lP.get(i),char_tempo);
			i++;
		}
		
		
	}
	
	
}
