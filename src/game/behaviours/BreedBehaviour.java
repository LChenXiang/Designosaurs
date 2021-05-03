package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that decides whether an actor is ready to breed and find a breed partner
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see game.actions.BreedAction
 * @see GameMap
 * @since 03/05/2021
 */

public class BreedBehaviour implements Behaviour{

    public BreedBehaviour() {}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }
}
