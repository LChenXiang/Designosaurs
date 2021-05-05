package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.actions.PickFruitAction;
import game.growable.Growable;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// TODO: Add pick fruit action here if location is growable
		if (map.locationOf(this).getGround() instanceof Growable){
			Growable growable = (Growable) map.locationOf(this).getGround();
			if (growable.getNumberOfRipeFruit() > 0){
				actions.add(new PickFruitAction(growable));
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}
}
