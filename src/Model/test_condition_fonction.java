/**
 * 
 */
package Model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author alexandre
 *
 */
public class test_condition_fonction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char N = 'N', S= 'S', E = 'E', O = 'O';
		Map carte = new Map(10,10);
		
		ArrayList<Character> l1 = new ArrayList<Character>();
		ArrayList<Character> l2 = new ArrayList<Character>();
		
		
		
		Player j1 = new Player();

		Survivor perso_test = new Survivor(j1,null,carte);
		l1.add(perso_test);
		perso_test.setStrength(12);
		j1.setId(1);
		j1.setEntities(l1);

		
		Player j2 = new Player();
		
		Survivor ennemi_cible = new Survivor(j2,null,carte);
		Survivor ennemi_cible2 = new Survivor(j2,null,carte);
		l2.add(ennemi_cible2);
		l2.add(ennemi_cible);
	
		j2.setId(2);
		j2.setEntities(l2);
		
		
		Cell cellule_temporaire = new Cell();
		cellule_temporaire.setDecor(Decor.KATANA);
		cellule_temporaire.setEntity_on(ennemi_cible);
		cellule_temporaire.setOwned_by(perso_test);
		//System.out.println(cellule_temporaire.getOwned_by()==null);
		cellule_temporaire.setPosition(new Point(2,3));
		ennemi_cible.setCell(cellule_temporaire);
		
		carte.setCell(cellule_temporaire);
		//System.out.println(carte.getGrid()[2][3]==cellule_temporaire);
		
		cellule_temporaire = new Cell();
		cellule_temporaire.setDecor(Decor.RABBIT);
		cellule_temporaire.setEntity_on(perso_test);
		cellule_temporaire.setOwned_by(null);
		cellule_temporaire.setPosition(new Point(2,4));
		perso_test.setCell(cellule_temporaire);
		
		carte.setCell(cellule_temporaire);
		
		
		cellule_temporaire = new Cell();
		cellule_temporaire.setDecor(Decor.KATANA);
		cellule_temporaire.setEntity_on(null);
		cellule_temporaire.setOwned_by(null);
		cellule_temporaire.setPosition(new Point(1,4));
		
		carte.setCell(cellule_temporaire);
		
		
		cellule_temporaire = new Cell();
		cellule_temporaire.setDecor(Decor.APPLE);
		cellule_temporaire.setEntity_on(ennemi_cible2);
		cellule_temporaire.setPosition(new Point(3,4));
		
		carte.setCell(cellule_temporaire);
		
		
		cellule_temporaire = new Cell();
		cellule_temporaire.setDecor(Decor.BASEBALL_BAT);
		cellule_temporaire.setEntity_on(null);
		cellule_temporaire.setOwned_by(null);
		cellule_temporaire.setPosition(new Point(2,5));
		
		carte.setCell(cellule_temporaire);
		

		cellule_temporaire = new Cell();
		cellule_temporaire.setDecor(Decor.BASEBALL_BAT);
		cellule_temporaire.setEntity_on(null);
		cellule_temporaire.setOwned_by(null);
		cellule_temporaire.setPosition(new Point(0,4));
		
		carte.setCell(cellule_temporaire);
		
		
		
		
		Condition_fonction c = new Condition_fonction();


		
		System.out.println(c.presence('N', j1, perso_test.getCell().getPosition(),carte));
		
		
		System.out.println(c.Scanloin(perso_test.getSightRange(),(Character) ennemi_cible,carte,new Point(2,4))=='S'); //probleme sur scanLoin
	}

}
