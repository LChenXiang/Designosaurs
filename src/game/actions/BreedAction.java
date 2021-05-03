package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action breeds with an Actor
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see Action
 * @see game.behaviours.BreedBehaviour
 * @see GameMap
 * @since 03/05/2021
 */

public class BreedAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}