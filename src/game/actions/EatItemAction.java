package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.items.EdibleItem;

/**
 * Action that allows a Dinosaur to eat an Item from the Ground and heals a certain amount of hitpoints
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see EdibleItem
 * @see Location
 * @see GameMap
 * @since 03/05/2021
 */

public class EatItemAction extends Action {

    /**
     * the edible item to be eaten
     */
    private EdibleItem edibleItem;

    /**
     * Constructor
     * @param item the item to be eaten
     */
    public EatItemAction(EdibleItem item) {
        edibleItem = item;
    }

    /**
     * Heals actor based on item's heal points and removes item from map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description about actor eating the item
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // TODO: CHANGE TO HEALPOINTS BASED ON ITEM
        int healPoints = 0; // placeholder
        actor.heal(healPoints);

        String result = actor + " eats " + edibleItem + " and heals for " + healPoints + " hitpoints.";

        Location here = map.locationOf(actor);
        here.removeItem(edibleItem);

        return result;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return description about actor eating the item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + edibleItem;
    }
}
