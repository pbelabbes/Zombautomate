package Model;

//import java.util.ArrayList;

public class Map {

	private int width,height;
	private Cell[][] grid;
	

	public int getWidth() {
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

	public Cell[][] getGrid() {
		return grid;
	}
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	//Constructeurs 
	public Map(int taille) {
		 grid=new Cell[taille][taille];
	}
}
