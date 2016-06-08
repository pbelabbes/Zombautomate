/**
 * 
 */
package Model;

import java.util.ArrayList;

/**
 * La classe abstraite Character regroupe les attributs et les fonctions communes aux survivants et aux zombies
 *
 */
public abstract class Character {

	int hp ;//points de vie 
	Player player;
	int strength ; 
	Cell cell;
	Automata automata;
	Map map;

}
