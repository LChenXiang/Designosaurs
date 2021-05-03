package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.DinosaurStatus;

/**
 * Action that allows a Dinosaur to eat from a Growable
 * @author Lin Chen Xiang
 * @see Actor
 * @see game.growable.Growable
 * @see Location
 * @see game.dinosaur.Dinosaur
 * @see DinosaurStatus
 * @see GameMap
 * @since 03/05/2021
 */

public class EatFromGrowableAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
