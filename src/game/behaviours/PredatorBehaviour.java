package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.EatPreyAction;
import game.dinosaur.DinosaurStatus;

/**
 * A class that decides whether the Actor can attack a nearby prey
 *
 * @author Lin Chen Xiang
 * @see Behaviour
 * @see Actor
 * @see EatPreyAction
 * @see game.dinosaur.Stegosaur
 * @see GameMap
 * @see Location
 * @since 03/05/2021
 */

public class PredatorBehaviour implements Behaviour {

    /**
     * Empty Constructor
     */
    public PredatorBehaviour() {
    }

    /**
     * Checks all exits of this actor to see if there are prey that can be attacked
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null if no prey to attack, new EatPreyAction if there are prey that can be attacked
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor() && destination.getActor().hasCapability(DinosaurStatus.ALLOSAUR_CAN_ATTACK)) {
                return new EatPreyAction(destination.getActor());
            }

        }

        return null;

    }
}
