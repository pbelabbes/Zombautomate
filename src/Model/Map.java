package Model;

//import java.util.ArrayList;
/**
 * La classe Map représente le terrain de jeu
 * Elle est caractérisée par sa largeur et sa hauteur ainsi que son tableau de cellule(=cases)
 * 
 *
 */
public class Map {

	//private int width,height;
	private Cell[][] grid;
	
	//Constructeurs 
	public Map(int width,int height) {
		//this.width=width;
		//this.height=height;
		 grid=new Cell[width][height];
	}
	
	//Getter et Setter
/*	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
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


	
}
