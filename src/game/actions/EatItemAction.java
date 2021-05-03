package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.DinosaurStatus;

/**
 * Action that allows a Dinosaur to eat an Item from the Ground
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see game.items.EdibleItem
 * @see Location
 * @see game.dinosaur.Dinosaur
 * @see DinosaurStatus
 * @see GameMap
 * @since 03/05/2021
 */

public class EatItemAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
