package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Gender;
import game.dinosaur.Stegosaur;
import game.growable.Bush;
import game.growable.Tree;
import game.watertile.Lake;
import game.watertile.WaterTile;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(),
				new VendingMachine(), new Bush(), new Lake());
		
		// first map
		List<String> map1 = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######.................................................~~~~~~~~~..........",
		".....#_____#.................................................~~~~~~~~~..........",
		".....#____V#.................................................~~~~~~~~~..........",
		".....###.###.................................................~~~~~~~~~..........",
		".............................................................~~~~~~~~~..........",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++.....................ww.......................................",
		"...............++.....................ww.................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.................................++.....",
		"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..................................++.++...",
		"..............+..........................................................++++...",
		".......++.+.........................~~~~..................................++....",
		".........+..........................~~~~........................................");

		// second map
		List<String> map2 = Arrays.asList(
		"................................................................................",
		"..........................~...........................~.........................",
		"...........................~.........................~.................+..+.....",
		"...................~++~.....~.......................~.....~++~............++....",
		"..++~~+...........~~++~~.....~.....................~.....~~++~~............w....",
		"....~+..........~~......~~.............................~~......~~.........w.....",
		"................++..ww..++.............................++..ww..++...............",
		"................~~......~~.............................~~......~~...............",
		"..................~~++~~.................................~~++~~.................",
		"...................~++~...................................~++~..................",
		"...+++........................~~.......~~~.......~~.............................",
		"~~~~~~w........................~~.....~~.~~.....~~..............................",
		"~~~~~~+.........................~~...~~...~~...~~........................w......",
		"~~~~~~++.........................~~.~~.....~~.~~......................w++.......",
		"~~~~~~++..........................~~~.......~~~.........................+.......",
		".++++................+..........................................................",
		"..................+..+.+........................................................",
		"...................+..+++.....................................+~~~~~~~~~~.......",
		"......................++.....................................++~~~~+............",
		"#######.........................................................~~~++...........",
		"#_____#..................................ww.......+++++........~~...............",
		"#V_____.................................~~~~........++++........................",
		"#_____#...............................~~~~~~~~.......++.........................",
		"#######.............................~~~~~~~~~~~~....++++........................",
		"................................................................................");
//		GameMap gameMap = new GameMap(groundFactory, map );
		JurassicParkGameMap gameMap1 = new JurassicParkGameMap(groundFactory, map1);
		world.addGameMap(gameMap1);
		JurassicParkGameMap gameMap2 = new JurassicParkGameMap(groundFactory, map2);
		world.addGameMap(gameMap2);

		gameMap1.addConnectingGameMap(gameMap2, 8);

		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap1.at(9, 0));


		// Place a pair of stegosaurs in the middle of the map
		gameMap1.at(30, 12).addActor(new Stegosaur(Gender.MALE));
		gameMap1.at(32, 12).addActor(new Stegosaur(Gender.FEMALE));
		// Place a pair of Brachiosaur
		gameMap1.at(20,12).addActor(new Brachiosaur(Gender.MALE));
		gameMap1.at(19,12).addActor(new Brachiosaur(Gender.FEMALE));

		world.run();
	}
}
