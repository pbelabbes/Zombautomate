package Model;

import java.util.ArrayList;

public class Map {

	int width,height;
	Cell[][] grid;
	
	public Map() {
		// TODO Auto-generated constructor stub
	}

	public Cell[][] init( int taille){
      return grid=new Cell[taille][taille];
       
     
	}
}
