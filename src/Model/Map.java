package Model;

import java.util.ArrayList;

public class Map {

	int width,height;
	Cell[][] grid;
	
	public Map() {
		
	}

	public Cell[][] init( int taille){
      return grid=new Cell[taille][taille];
       
     
	}
}
