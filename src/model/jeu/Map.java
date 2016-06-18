package model.jeu;
import model.automate.*;


import java.awt.Point;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import model.decors.*;
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
		int debut = grid.length;
		return grid.length;
	}

	/*
	public void setWidth(int width) {
		this.width = width;
	}
*/
	public int getHeight() {
		int debut = grid[0].length;
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
	public void setAutomata(Automata a, Point pos, Character perso)
	{
		a.setPosition(pos);
		
		for(int x= pos.x ; x < a.getEtats()+pos.x; x++)
		{
			for(int y = pos.y ; y < a.getInputs()+pos.y ; y++)
			{
				if(x<getWidth() && y< getHeight())
				{
					CaseAutomate case_tempo = a.getStates()[x-pos.x][y-pos.y];
					if(case_tempo!=null)
					{	
						this.grid[x][y].setOwned_by(perso);
						this.grid[x][y].setDecor(Decor.getDecor(case_tempo.getAction()));
//						this.grid[x][y].setPosition(new Point(x,y));
						this.grid[x][y].setCaseAutomate(case_tempo);
					}
				}
				else 	System.out.println("dimensions de map suspectes");
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
//		c.setCell(this.getGrid()[p.x][p.y]);
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
			while(grid[x][y].getEntity_on()!=null && grid[x][y].getDecor() != Decor.ROCK);
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
	 * fait apparaitre un zombie à un endroit aléatoire de la carte et le retourne
	 * @param lC liste des personnages en jeu (le zombie y sera ajouté
	 * @param p0 joueur qui controle les zombies
	 * @return  le nouveau zombie
	 */
	public Zombie random_pop_zombie(ArrayList<Character> lC, Player p0)
	{
		Zombie new_z = new Zombie((Zombie) p0.getEntities().get(0));
		Cell cellule;
		do
		{
			int rand_x = (int) (getWidth()*Math.random());
			int rand_y = (int) (getHeight()*Math.random());
		
		
			cellule = grid[rand_x][rand_y];
			
		}while (cellule.getEntity_on()!=null);
		
		cellule.setEntity_on(new_z);
		lC.add(new_z);
		
		return new_z;		
	}

	
	/**
	 * Fait apparaitre un zombie sur la cellule donnée. Il faut cependant être certain qu'aucune entité n'est présent sur l'emplacement indiqué
	 * @param cellule cellule d'apparition du zombie
	 * @param p0 Joueur 0 (controle les zombies)
	 * @param a automate de zombie
	 * @param map carte sur laquelle se déroule la partie
	 */
	public void pop_zombie(Cell cellule, Player p0, Automata a, ArrayList<Character> lC)
	{
		Zombie new_z = new Zombie(p0,a,this);
		cellule.setEntity_on(new_z);
		lC.add(new_z);		
	}
	
	
	/**
	 * fait apparaitre des zombies aléatoirement sur la map
	 * @param lC liste des personnages
	 * @param p0 Joueur qui controle les zombies
	 * @param nb nombre de nouveau zombis
	 */
	public void random_pop_zombies(ArrayList<Character> lC, Player p0, int nb)
	{
		int dimx = getWidth();
		int dimy = getHeight();
		Automata auto_zombie = p0.getEntities().get(0).getAutomata();

		
		
		for(int i = 0 ; i < nb ; i++)
		{
			Cell cellule;
			do
			{
				int rand_x = (int) (dimx*Math.random());
				int rand_y = (int) (dimy*Math.random());
			
			
				cellule = grid[rand_x][rand_y];
				
			}while (cellule.getEntity_on()!=null);
			
			pop_zombie(cellule,p0, auto_zombie, lC);
			
		}
		
	}
	
	public void print_map()
	{
		int h=getHeight();
		int w=getWidth();
		for (int y = 0 ; y< h ; y++)
		{
			for(int x = 0 ; x < w ; x++)
			{
//				grid[x][y].print_cell();
				if(grid[x][y].getEntity_on()!=null)		System.out.printf("%d",grid[x][y].getEntity_on().getPlayer().getId());
				else 	System.out.print(".");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public void init_demo(int m) 
	{
		int h = getHeight();
		int w = getWidth();
		for(int x = 0; x<w ; x++)
		{
			for(int y = 0 ; y<h ; y++)
			{
				grid[x][y] = new Cell(new Point(x,y));
				grid[x][y].setDecor(Decor.GRASS);
			}
		}
		if(m==1)
		{
			grid[6][5].setDecor(Decor.KATANA);
			grid[5][6].setDecor(Decor.RABBIT);
			grid[10][5].setDecor(Decor.ROCK);
			grid[10][7].setDecor(Decor.ROCK);
			grid[9][6].setDecor(Decor.ROCK);
			grid[11][6].setDecor(Decor.ROCK);
		}
	}
}
