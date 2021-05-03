package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;
import game.dinosaur.DinosaurStatus;

/**
 * Class that decides whether an actor is hungry and finds food if so
 *
 * @author Lin Chen Xiang
 * @see Actor
 * @see Location
 * @see Dinosaur
 * @see DinosaurStatus
 * @see GameMap
 * @since 03/05/2021
 */

abstract public class HungerBehaviour implements Behaviour{

    /**
     * Find a suitable food
     * @return action to be done
     */
    abstract Action findFood();

}
