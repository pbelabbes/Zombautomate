package Model;

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

public class Map {

//	private int width,height;
	private Cell[][] grid;
	
	//Constructeurs 
	// Petite modif d'alex : la carte peut maintenant être rectangulaire.
	public Map(int width, int height) {
		 grid=new Cell[width][height];
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
	}


	public void setCell(Cell cell)
	{
		grid[cell.getPosition().x][cell.getPosition().y] = cell;
	}
	
}
