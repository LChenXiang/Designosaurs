package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;
import game.dinosaur.DinosaurStatus;

/**
 * Class that decides whether a herbivore is hungry and finds food if so
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see Location
 * @see game.dinosaur.HerbivoreDinosaur
 * @see DinosaurStatus
 * @see GameMap
 * @since 03/05/2021
 */

public class HerbHungerBehaviour extends HungerBehaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }

    @Override
    Action findFood() {
        return null;
    }
}
