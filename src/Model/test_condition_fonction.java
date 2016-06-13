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
		
		
		
		Player j1 = new Player( 1,"toto", 20);

		Survivor perso_test = new Survivor(j1,null,carte);
		Survivor allie = new Survivor(j1,null,carte);

		l1.add(perso_test);
		l1.add(allie);
		perso_test.setStrength(12);
		j1.setEntities(l1);

		
		Player j2 = new Player(2,"tata",20);
		
		Survivor ennemi_cible = new Survivor(j2,null,carte);
		Survivor ennemi_cible2 = new Survivor(j2,null,carte);
		l2.add(ennemi_cible2);
		l2.add(ennemi_cible);
		
		j2.setEntities(l2);
		
		Point poscell=new Point (1,1);
		Cell cellule_temporaire = new Cell(poscell);
		cellule_temporaire.setDecor(Decor.KATANA);
		cellule_temporaire.setEntity_on(ennemi_cible);
		cellule_temporaire.setOwned_by(perso_test);
		//System.out.println(cellule_temporaire.getOwned_by()==null);
		//cellule_temporaire.setPosition(new Point(2,3));
		ennemi_cible.setCell(cellule_temporaire);
		
		carte.setCell(cellule_temporaire);
		//System.out.println(carte.getGrid()[2][3]==cellule_temporaire);
		poscell=new Point();
		poscell.x=1;
		poscell.y=2;
		Cell cellule = new Cell(poscell);
		cellule.setDecor(Decor.RABBIT);
		cellule.setEntity_on(perso_test);
		cellule.setOwned_by(null);
		//cellule_temporaire.setPosition(new Point(2,4));
		perso_test.setCell(cellule_temporaire);
		
		carte.setCell(cellule_temporaire);
		poscell=new Point();
		poscell.x=9;
		poscell.y=1;
		cellule_temporaire = new Cell(poscell);
		cellule_temporaire.setDecor(Decor.KATANA);
		cellule_temporaire.setEntity_on(null);
		cellule_temporaire.setOwned_by(null);
		//cellule_temporaire.setPosition(new Point(1,4));
		
		carte.setCell(cellule_temporaire);
		poscell=new Point();
		poscell.x=9;
		poscell.y=9;
		cellule_temporaire = new Cell(poscell);
		cellule_temporaire.setDecor(Decor.APPLE);
		cellule_temporaire.setEntity_on(ennemi_cible2);
		//cellule_temporaire.setPosition(new Point(3,4));
		
		carte.setCell(cellule_temporaire);
		poscell=new Point();
		poscell.x=3;
		poscell.y=3;
		cellule_temporaire = new Cell(poscell);
		cellule_temporaire.setDecor(Decor.BASEBALL_BAT);
		cellule_temporaire.setEntity_on(null);
		cellule_temporaire.setOwned_by(null);
		//cellule_temporaire.setPosition(new Point(2,5));
		
		carte.setCell(cellule_temporaire);
		poscell=new Point();
       poscell.x=3;
       poscell.y=0;
		cellule_temporaire = new Cell(poscell);
		cellule_temporaire.setDecor(Decor.BASEBALL_BAT);
		cellule_temporaire.setEntity_on(null);
		cellule_temporaire.setOwned_by(null);
		//cellule_temporaire.setPosition(new Point(0,4));
		
		carte.setCell(cellule_temporaire);
	
		poscell=new Point();
		poscell.x=9;
		poscell.y=2;
		cellule_temporaire = new Cell(poscell);
		cellule_temporaire.setDecor(Decor.RABBIT);
		cellule_temporaire.setEntity_on(allie);
		cellule_temporaire.setOwned_by(null);
		//cellule_temporaire.setPosition(new Point(2,4));
		perso_test.setCell(cellule_temporaire);
		
		carte.setCell(cellule_temporaire);
		
		
		ScanLoin c = new ScanLoin(Decor.BASEBALL_BAT,'S');
		//c.execute(cellule);
       
		c.setRayon(4);
		System.out.println(c.execute(cellule));
		
		//System.out.println(perso_test.getSightRange());
		//System.out.println(c.Scanloin(perso_test.getSightRange(),Decor.BASEBALL_BAT,carte,new Point(2,4))); //probleme sur scanLoin
	}

}
