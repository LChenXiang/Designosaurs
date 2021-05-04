package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.dinosaur.Dinosaur;
import game.dinosaur.DinosaurStatus;

/**
 * Class that decides whether an actor is hungry and finds food if so
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see Location
 * @see GameMap
 * @see CarniHungerBehaviour
 * @see HerbHungerBehaviour
 * @since 05/05/2021
 */

public abstract class HungerBehaviour implements Behaviour{

    /**
     * Finds the nearest suitable food source in the map.
     * @param here location of this actor
     * @param actor the actor looking for food
     * @param map World map
     * @return null if no food source found, Action to start moving towards food
     */
    public abstract Action findFood(Location here, Actor actor, GameMap map);

}
