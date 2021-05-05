package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.growable.Growable;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a Location with target Growable/Item, or does something when the actor reaches the Location
 *
 * @author Lin Chen Xiang
 * @see Behaviour
 * @see Actor
 * @see Growable
 * @see Item
 * @see Action
 * @see GameMap
 * @see Location
 * @since 03/05/2021
 */

public class GoToLocation implements Behaviour {

    private Location there = null;
    private Growable growable = null;
    private Item item = null;
    private Action action = null;

    private Behaviour self = this;

    /**
     * Constructor that takes in Growable
     *
     * @param there    the Location of the Growable
     * @param growable the Growable to be reached
     * @param action   the Action to be done when reaching there
     */
    public GoToLocation(Location there, Growable growable, Action action) {
        this.there = there;
        this.growable = growable;
        this.action = action;
    }

    /**
     * Constructor that takes in Item
     *
     * @param there  the Location of the Item
     * @param item   the Item to be reached
     * @param action the Action to be done when reaching there
     */
    public GoToLocation(Location there, Item item, Action action) {
        this.there = there;
        this.item = item;
        this.action = action;
    }

    /**
     * Decides the current Action for this Actor
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return MoveActorAction if Actor has not reached target, or action if Actor reached target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (map.contains(actor)) {
            boolean flag = false;
            // check if growable is set in constructor, if it is null means item is set
            if (growable != null) {
                // check if growable still exists at that location, if so check if it still has any fruit on it
                if (there.getGround() != growable || growable.getNumberOfRipeFruit() < 1) {
                    return null; // no such growable/no more fruit, reset behaviour
                }
            } else
                // check if item still exists at that location
                for (Item item : there.getItems()) {
                    if (item == this.item) {
//                    if (item.getClass() == this.item.getClass()) // check if such an item still exists
//                        this.item = item;
                        flag = true;
                        break;
                    }
                }
            if (!flag) { // item doesn't exist, reset behaviour
                return null;
            }
        } else { // actor missing, can't do anything
            return null;
        }

        Location here = map.locationOf(actor);

        int currentDistance = distance(here, there); // no change
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                // actor hasn't reached its goal yet, keep going
                if (newDistance < currentDistance) {
                    return (new MoveActorAction(destination, exit.getName()) {
                        @Override
                        public Action getNextAction() { // override next action as current action to chain follow
                            return self.getAction(actor, map);
                        }
                    });
                }
            }
        }
        if (currentDistance>1) {
            // actor still not at goal, continue moving towards it
            return (new DoNothingAction() {
                @Override
                public Action getNextAction() { // override next action as current action continue moving
                    return self.getAction(actor,map);}
            });
        }

        else
            return action; // actor is already beside target, do action
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}


