package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.DinosaurStatus;
import game.items.EdibleItem;
import game.items.Egg;

/**
 * Special Action that allows a Dinosaur to lay an Egg.
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see edu.monash.fit2099.engine.Location
 * @see Egg
 * @see game.dinosaur.DinosaurStatus
 * @see GameMap
 * @since 03/05/2021
 */

public class LayEggAction extends Action {

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description stating that the actor laid an egg
     */

    @Override
    public String execute(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        EdibleItem egg = new Egg("Egg", 'O');
        here.addItem(egg);
        // TODO: MAY NEED TO ADD ENUM OR SMTH TO INDICATE WHICH DINOSAUR

        actor.removeCapability(DinosaurStatus.PREGNANT); // no longer pregnant
        return actor + " lays an egg.";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return description about actor laying an egg
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays an egg.";
    }
}
