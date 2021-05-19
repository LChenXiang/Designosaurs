package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Parent class for all moving related Behaviours
 *
 * @author Lin Chen Xiang
 * @see FollowBehaviour
 * @see GoToLocation
 * @see Location
 * @since 19/05/2021
 * @version 1.1
 */

public class MovingBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
