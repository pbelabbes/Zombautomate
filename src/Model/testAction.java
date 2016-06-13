package Model;

import java.awt.Point;
import java.util.ArrayList;

public class testAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//		Point C = new Point(1,1) ;
		//		Point N = new Point(0,1);
		//		Point S = new 
		char N = 'N', S= 'S', E = 'E', O = 'O';
		Map carte = new Map(10,10);

		carte.init_map();

		Cell[][] tab = carte.getGrid() ;

		ArrayList<Character> l1 = new ArrayList<Character>();
		ArrayList<Character> l2 = new ArrayList<Character>();

		Player j1 = new Player();
		j1.setName("joueur1");

		Survivor perso_test = new Survivor(j1,null,carte);
		j1.addStone(20);
		j1.addSeed(20);
		
		l1.add(perso_test);
		perso_test.setStrength(12);
		j1.setId(1);
		j1.setEntities(l1);


		Player j2 = new Player();
		j2.setName("joueur2");
		j2.addStone(30);
		j2.addSeed(40);
		

		Survivor ennemi_cible = new Survivor(j2,null,carte);
		Survivor ennemi_cible2 = new Survivor(j2,null,carte);
		l2.add(ennemi_cible2);
		l2.add(ennemi_cible);

		j2.setId(2);
		j2.setEntities(l2);

		tab[0][0].setEntity_on(perso_test);
		//tab[0][0].setOwned_by(perso_test);
		//System.out.println(cellule_temporaire.getOwned_by()==null);


		perso_test.setCell(tab[0][0]);
		perso_test.showaround();
		j1.show();

		ennemi_cible.setCell(tab[0][1]);
		ennemi_cible2.setCell(tab[7][7]) ;
		j2.show(); 

		//perso_test.deplacer(S);
		//perso_test.pick(tab[2][4]);
//		perso_test.drop(tab[2][4]);
//		perso_test.plant(tab[2][2]);
		
//		perso_test.act(Action.MOVE,O);
		perso_test.act(Action.ATTACK,S);
//		perso_test.act(Action.PICK,O);
//		perso_test.act(Action.STEAL,O);
		
		System.out.println("\n\n");
		perso_test.showaround();
		j1.show();




	}

}
