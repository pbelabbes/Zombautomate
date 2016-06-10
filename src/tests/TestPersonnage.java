package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Automata;
import Model.Character;
import Model.Map;
import Model.Player;
import Model.Survivor;

public class TestPersonnage {

	
	@Test
	public void testCharacter() {
		Player p = new Player(0, "Pierre", 4);
		Automata a = new Automata(0, 15, 15);
		Map m = new Map(100, 100);
		Character c = new Survivor(p, a, m);
		
		assertSame(p, c.getPlayer());
		assertSame(a, c.getAutomata());
		assertSame(m,c.getMap());
	}

	@Test
	public void testGetHp() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupHp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSightRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStrength() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStrength() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCell() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAutomata() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAutomata() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeplacer() {
		fail("Not yet implemented");
	}

	@Test
	public void testIs_alive() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlay() {
		fail("Not yet implemented");
	}

}
