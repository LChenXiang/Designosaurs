package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Gender;
import game.dinosaur.Stegosaur;
import game.growable.Tree;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
//		GameMap gameMap = new GameMap(groundFactory, map );
		GameMap gameMap = new JurassicParkGameMap(groundFactory, map);
		world.addGameMap(gameMap);

		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));

		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur("Stegosaur", Gender.MALE));
		gameMap.at(32, 12).addActor(new Stegosaur("Stegosaur", Gender.FEMALE));
		// Place a pair of Brachiosaur
		gameMap.at(20,12).addActor(new Brachiosaur("Brachiosaur", Gender.MALE));
		gameMap.at(19,12).addActor(new Brachiosaur("Brachiosaur", Gender.FEMALE));

		world.run();
	}
}
