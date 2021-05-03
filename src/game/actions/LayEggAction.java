package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action that allows a Dinosaur to lay an Egg.
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see edu.monash.fit2099.engine.Location
 * @see game.items.EdibleItem
 * @see game.dinosaur.DinosaurStatus
 * @see GameMap
 * @since 03/05/2021
 */

public class LayEggAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays an egg.";
    }
}
