package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Model.*;
public class AutomataTest {

	@Test
	public void testAutomata() {
		int e,w,h;
		Automata a;
		
		//test1
		e=0;
		w=4;
		h=5;
		a=new Automata(e,w,h);
		if (e!=a.getEtatCourant()){
			fail("erreur etat courant");
		}
		if(w!=a.getEtats()){
			fail("erreur largeur");
		}
		if (h!=a.getInputs()){
		fail("erreur hauteur");
		}
		
		
		
		//test2
		e=-4;
		w=12;
		h=0;
		a=new Automata(e,w,h);
		if (0!=a.getEtatCourant()){
			fail("erreur etat courant");
		}
		if(0!=a.getEtats()){
			fail("erreur largeur");
		}
		if (0!=a.getInputs()){
		fail("erreur hauteur");
		}
	}

	@Test
	public void testAutomate() {
		/*public transfer(int etat_courant,Condition condition, Action action , char direction, int priority, int etat_futur) {
			//super();
			this.etat_courant = etat_courant;
			this.etat_futur = etat_futur;
			this.action = action;
			this.condition = condition;
			this.direction = direction;
			this.priority = priority; 
		}*/
		XMLReader xml=new XMLReader() ;
		ArrayList<ArrayList<transfer>> liste;
		liste=xml.read("/home/alice/Zombautomate/ocaml/equipe.xml");
		fail("Not yet implemented");
	}

	@Test
	public void testProportion() {
		
		fail("Not yet implemented");
	}


}
