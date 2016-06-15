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
		ArrayList<transfer> t=new ArrayList<transfer>();
		t.add(new transfer(0,new Presence('N',"ennemi"),Action.ATTACK, 'N', 1,0));
		t.add(new transfer(0,new ScanLoin(Decor.APPLE,'N'),Action.MOVE, 'N', 0, 1));
		t.add(new transfer(1, new ScanLoin(Decor.APPLE,'N'), Action.MOVE, 'N', 0, 1));
		t.add(new transfer(1, new ScanProche(Decor.APPLE,'N'),Action.PICK, 'N', 1, 0));
		Automata a=new Automata(0,2,2);
		a.automate(t);
		System.out.println("test automate");
		CaseAutomate tmp = new CaseAutomate(t.get(0).getEtat_futur(), t.get(0).getAction(),  t.get(0).getCondition(),  t.get(0).getPriority(),  t.get(0).getDirection());
		if (a.getStates()[0][0].equals(tmp)){
			System.out.println(a.getStates()[0][0]);
			System.out.println(tmp);
			fail("case 0 0");
		}
		if (a.getStates()[0][1].equals(new CaseAutomate(t.get(1).getEtat_futur(), t.get(1).getAction(),  t.get(1).getCondition(),  t.get(1).getPriority(),  t.get(1).getDirection()))){
			fail("case 0 1");
		}
		if (a.getStates()[1][0].equals(new CaseAutomate(t.get(2).getEtat_futur(), t.get(2).getAction(),  t.get(2).getCondition(),  t.get(2).getPriority(),  t.get(2).getDirection()))){
			fail("case 1 0");
		}
		if (a.getStates()[1][1].equals(new CaseAutomate(t.get(3).getEtat_futur(), t.get(3).getAction(),  t.get(3).getCondition(),  t.get(3).getPriority(),  t.get(3).getDirection()))){
			fail("case 1 1");
		}
	}

	@Test
	public void testProportion() {
		
		fail("Not yet implemented");
	}


}
