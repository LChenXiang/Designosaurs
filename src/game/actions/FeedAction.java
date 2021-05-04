package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.Fruit;

/**
 * Action that allows a Dinosaur to eat an Item from the Ground and heals a certain amount of hitpoints
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see game.Player
 * @see game.dinosaur.Dinosaur
 * @see Item
 * @see game.VendingMachine
 * @see GameMap
 * @since 05/05/2021
 */

public class FeedAction extends Action {

    /**
     * the actor to be fed
     */
    private Actor target;

    /**
     * the item being fed to target
     */
    private Item item;

    /**
     * Constructor
     * @param target the actor to be fed
     * @param item the item being fed to target
     */
    public FeedAction(Actor target, Item item) {
        this.target = target;
        this.item = item;
    }

    /**
     * Heals target based on healing of item and removes the item from player inventory
     * @param actor Player who is feeding the target
     * @param map The map the actor is on.
     * @return description of process of feeding the target
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = actor + " feeds " + target + " with " + item;
        // TODO: Get heal points from Item
        int healPoints = 0; // placeholder
        target.heal(healPoints);
        result += System.lineSeparator() + target + " heals for " + healPoints + " hitpoints.";

        // add EcoPoints if item being fed is a fruit
        if (item instanceof Fruit) {
            // TODO: EcoPoints += 10
            // sysmsg player gains 10 ecopoints
        }

        result += System.lineSeparator() + actor + " loses one " + item + ".";
        actor.removeItemFromInventory(item);

        return result;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return description of player feeding target with this item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target + " with " + item;
    }
}
